package View.Controller;

import Config.DBConfig; // <-- Import DBConfig
import Dao.UserDAO;
import Model.UserModel;
import Service.UserService;
import Session.Session;
import View.Login.LoginView;
import View.ViewManager;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection; // <-- Import Connection
import java.sql.SQLException;

public class LoginController {

    private final LoginView view;
    private final ViewManager viewManager;
    private UserService userService; // Tidak perlu 'final' jika diinisialisasi di try-catch

    public LoginController(LoginView view, ViewManager viewManager) {
        this.view = view;
        this.viewManager = viewManager;

        // --- Inisialisasi Service dengan Koneksi Database ---
        try {
            // 1. Dapatkan koneksi dari DBConfig
            Connection connection = new DBConfig().getConnection();
            // 2. Berikan koneksi tersebut saat membuat UserDAO
            this.userService = new UserService(new UserDAO(connection));
        } catch (SQLException e) {
            e.printStackTrace();
            // Tampilkan error jika koneksi gagal dibuat saat pertama kali
            showAlert("Error Database Kritis", "Tidak dapat terhubung ke database. Aplikasi akan ditutup.");
            Platform.exit(); // Tutup aplikasi jika database tidak bisa diakses
        }

        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Menonaktifkan tombol login jika field kosong
        view.getLoginBtn().disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                view.getUserTxt().getText().trim().isEmpty() ||
                                        view.getPassTxt().getText().trim().isEmpty(),
                        view.getUserTxt().textProperty(),
                        view.getPassTxt().textProperty()
                )
        );

        // Logika untuk tombol Login
        view.getLoginBtn().setOnAction(event -> handleLogin());

        // Logika untuk label "Daftar"
        view.getRegisterLabel().setOnMouseClicked(event -> {
            viewManager.showRegisterView();
        });
    }

    private void handleLogin() {
        String username = view.getUserTxt().getText();
        String password = view.getPassTxt().getText();
        boolean isPemilik = view.getPemilikCheckBox().isSelected();
        UserModel.Role expectedRole = isPemilik ? UserModel.Role.PENYEWA : UserModel.Role.USERS;

        try {
            boolean loginSuccess = userService.login(username, password);

            if (loginSuccess) {
                UserModel loggedInUser = Session.get();
                if (loggedInUser.getRoles() == expectedRole) {
                    System.out.println("Login berhasil sebagai: " + loggedInUser.getRoles());
                    if (loggedInUser.getRoles() == UserModel.Role.PENYEWA) {
                        viewManager.showAdminDashboard();
                    } else {
                        viewManager.showUserDashboard();
                    }
                } else {
                    showAlert("Login Gagal", "Peran yang Anda pilih tidak sesuai dengan akun Anda.");
                    Session.clear();
                }
            } else {
                showAlert("Login Gagal", "Username atau password salah.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error Database", "Terjadi kesalahan saat proses login.");
        }
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
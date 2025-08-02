package View.Controller;

import Config.DBConfig;
import Dao.UserDAO;
import Model.UserModel;
import Service.UserService;
import View.Register.RegisterView;
import View.ViewManager;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.SQLException;

public class RegisterController {

    private final RegisterView view;
    private final ViewManager viewManager;
    private UserService userService;

    public RegisterController(RegisterView view, ViewManager viewManager) {
        this.view = view;
        this.viewManager = viewManager;

        try {
            Connection connection = new DBConfig().getConnection();
            this.userService = new UserService(new UserDAO(connection));
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Error Database Kritis", "Tidak dapat terhubung ke database.");
        }

        attachEventHandlers();
    }

    private void attachEventHandlers() {
        // Logika untuk link "Masuk"
        view.getLoginLabel().setOnMouseClicked(event -> viewManager.showLoginView());

        // Logika untuk menonaktifkan tombol register
        BooleanBinding isEmailEmpty = view.getEmailTxt().textProperty().isEmpty();
        BooleanBinding isPassEmpty = view.getPassTxt().textProperty().isEmpty();
        BooleanBinding isConfirmPassEmpty = view.getConfirmPassTxt().textProperty().isEmpty();
        BooleanBinding isTermsNotChecked = view.getTermsCheckBox().selectedProperty().not();

        view.getRegisterBtn().disableProperty().bind(
                isEmailEmpty.or(isPassEmpty).or(isConfirmPassEmpty).or(isTermsNotChecked)
        );

        // Logika saat tombol "Daftar" diklik
        view.getRegisterBtn().setOnAction(event -> handleRegister());
    }

    private void handleRegister() {
        // 1. Validasi input di frontend
        if (!view.getPassTxt().getText().equals(view.getConfirmPassTxt().getText())) {
            showError("Password dan konfirmasi tidak cocok!");
            return;
        }

        RadioButton selectedRoleRadio = (RadioButton) view.getRoleToggleGroup().getSelectedToggle();
        if (selectedRoleRadio == null) {
            showError("Silakan pilih peran Anda!");
            return;
        }

        // 2. Siapkan data untuk dikirim ke service
        String email = view.getEmailTxt().getText();
        String password = view.getPassTxt().getText();
        String roleText = selectedRoleRadio.getText();

        // Konversi teks peran menjadi Enum Role
        UserModel.Role role = "Pemilik Kos".equals(roleText) ? UserModel.Role.PENYEWA : UserModel.Role.USERS;

        // 3. Panggil service untuk registrasi
        try {
            boolean isSuccess = userService.register(email, password, role);

            if (isSuccess) {
                showAlert(AlertType.INFORMATION, "Registrasi Berhasil", "Akun Anda telah berhasil dibuat. Silakan login.");
                viewManager.showLoginView(); // Arahkan ke halaman login
            } else {
                showError("Email ini sudah terdaftar. Silakan gunakan email lain.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("Terjadi kesalahan pada database saat registrasi.");
        }
    }

    // Metode helper untuk menampilkan pesan
    private void showError(String message) {
        if (view.getErrorLabel() != null) {
            view.getErrorLabel().setText(message);
            view.getErrorLabel().setVisible(true);
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

    // Anda juga bisa menambahkan versi lain untuk notifikasi sukses
    private void showAlert(AlertType type, String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }
}
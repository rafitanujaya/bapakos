package View.Controller;

import View.Login.LoginView;
import View.ViewManager;
import javafx.beans.binding.Bindings;

public class LoginController {

    public LoginController(LoginView view, ViewManager viewManager) {

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
        view.getLoginBtn().setOnAction(event -> {
            String role;
            // Cek apakah checkbox "Sebagai Pemilik" dicentang
            if (view.getPemilikCheckBox().isSelected()) {
                role = "Pemilik Kos";
                viewManager.showAdminDashboard();
            } else {
                role = "Penyewa Kos";
                viewManager.showUserDashboard();
            }
            System.out.println("Login berhasil sebagai: " + role);
        });

        // Logika untuk label "Daftar"
        view.getRegisterLabel().setOnMouseClicked(event -> {
            System.out.println("Label daftar diklik, pindah ke halaman register...");
            viewManager.showRegisterView();
        });
    }
}
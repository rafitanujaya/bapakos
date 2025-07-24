package Controller;

import View.LoginView;
import javafx.beans.binding.Bindings;
import javafx.scene.control.RadioButton;

public class LoginController {

    public LoginController(LoginView view, ViewManager viewManager) {
        // Sembunyikan label error pada awalnya

        view.getErrorLabel().setVisible(false);

        // 1. Logika untuk menonaktifkan tombol login jika field kosong
        view.getLoginBtn().disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                view.getUserTxt().getText().trim().isEmpty() ||
                                        view.getPassTxt().getText().trim().isEmpty(),
                        view.getUserTxt().textProperty(),
                        view.getPassTxt().textProperty()
                )
        );

        // 2. Logika untuk tombol Login
        view.getLoginBtn().setOnAction(event -> {
            String email = view.getUserTxt().getText();
            String password = view.getPassTxt().getText();

            // Dapatkan radio button yang dipilih
            RadioButton selectedRoleRadio = (RadioButton) view.getRoleToggleGroup().getSelectedToggle();
            String selectedRole = selectedRoleRadio.getText(); // Hasilnya "Pemilik Kos" atau "Penyewa Kos"

            // Ganti ini dengan validasi ke database Anda
            // Contoh validasi sederhana
            boolean loginSukses = false;
            if (selectedRole.equals("Pemilik Kos") && email.equals("pemilik@gmail.com") && password.equals("pemilik123")) {
                loginSukses = true;
            } else if (selectedRole.equals("Penyewa Kos") && email.equals("penyewa@gmail.com") && password.equals("penyewa123")) {
                loginSukses = true;
            }

            if (loginSukses) {
                view.getErrorLabel().setVisible(false);
                System.out.println("Login berhasil sebagai: " + selectedRole);
                viewManager.showHomepageView();
            } else {
                view.getErrorLabel().setText("Kombinasi email, password, atau peran salah!");
                view.getErrorLabel().setVisible(true);
            }
        });

        // 3. Logika untuk tombol Register
        view.getRegisterButton().setOnAction(event -> {
            System.out.println("Tombol register ditekan, pindah ke halaman register...");
            viewManager.showRegisterView();
        });
    }
}
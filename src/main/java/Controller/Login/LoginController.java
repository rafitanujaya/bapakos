package Controller.Login;

import Controller.ViewManager;
import View.Login.LoginView;
import javafx.beans.binding.Bindings;
import javafx.scene.control.RadioButton;

public class LoginController {

    public LoginController(LoginView view, ViewManager viewManager) {
        // Sembunyikan label error pada awalnya
        view.getErrorLabel().setVisible(false);

        // Menonaktifkan tombol login jika field kosong (logika ini tetap berguna)
        view.getLoginBtn().disableProperty().bind(
                Bindings.createBooleanBinding(() ->
                                view.getUserTxt().getText().trim().isEmpty() ||
                                        view.getPassTxt().getText().trim().isEmpty(),
                        view.getUserTxt().textProperty(),
                        view.getPassTxt().textProperty()
                )
        );

        // Logika untuk tombol Login yang sudah diubah
        view.getLoginBtn().setOnAction(event -> {
            // Dapatkan radio button yang dipilih
            RadioButton selectedRoleRadio = (RadioButton) view.getRoleToggleGroup().getSelectedToggle();

            // Pengaman jika tidak ada radio button yang dipilih
            if (selectedRoleRadio == null) {
                view.getErrorLabel().setText("Silakan pilih peran Anda!");
                view.getErrorLabel().setVisible(true);
                return;
            }

            String selectedRole = selectedRoleRadio.getText();

            // Login sekarang selalu dianggap berhasil.
            // Kita hanya perlu mengecek peran untuk navigasi.
            System.out.println("Login berhasil sebagai: " + selectedRole);

            if (selectedRole.equals("Pemilik Kos")) {
                // Jika peran adalah Pemilik Kos, arahkan ke dashboard admin
                viewManager.showAdminDashboard();
            } else if (selectedRole.equals("Penyewa Kos")) {
                // Jika peran adalah Penyewa Kos, arahkan ke dashboard user
                viewManager.showUserDashboard();
            }
        });

        // Logika untuk tombol Register tetap sama
        view.getRegisterButton().setOnAction(event -> {
            System.out.println("Tombol register ditekan, pindah ke halaman register...");
            viewManager.showRegisterView();
        });
    }
}
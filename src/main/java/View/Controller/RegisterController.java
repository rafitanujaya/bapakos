package View.Controller;

import View.ViewManager;
import View.Register.RegisterView;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.RadioButton;
import java.util.ArrayList;
import java.util.List;

public class RegisterController {

    private List<String> existingEmails = new ArrayList<>();

    public RegisterController(RegisterView view, ViewManager viewManager) {
        existingEmails.add("user@gmail.com");

        // Pastikan error label disembunyikan pada awalnya
        if (view.getErrorLabel() != null) {
            view.getErrorLabel().setVisible(false);
        }

        // --- Event handler baru untuk teks "Masuk" ---
        view.getLoginLabel().setOnMouseClicked(event -> {
            viewManager.showLoginView();
        });

        // --- Logika untuk menonaktifkan tombol register ---
        BooleanBinding isEmailEmpty = view.getEmailTxt().textProperty().isEmpty();
        BooleanBinding isPassEmpty = view.getPassTxt().textProperty().isEmpty();
        BooleanBinding isConfirmPassEmpty = view.getConfirmPassTxt().textProperty().isEmpty();
        BooleanBinding isTermsNotChecked = view.getTermsCheckBox().selectedProperty().not();

        view.getRegisterBtn().disableProperty().bind(
                isEmailEmpty.or(isPassEmpty).or(isConfirmPassEmpty).or(isTermsNotChecked)
        );

        // --- Logika saat tombol "Daftar" diklik ---
        view.getRegisterBtn().setOnAction(event -> {
            String email = view.getEmailTxt().getText();
            String password = view.getPassTxt().getText();

            // Cek duplikasi email
            if (existingEmails.contains(email)) {
                showError(view, "Email ini sudah terdaftar!");
                return;
            }

            // Cek kecocokan password
            if (!password.equals(view.getConfirmPassTxt().getText())) {
                showError(view, "Password dan konfirmasi tidak cocok!");
                return;
            }

            // Ambil peran yang dipilih dari RadioButton
            RadioButton selectedRoleRadio = (RadioButton) view.getRoleToggleGroup().getSelectedToggle();
            if (selectedRoleRadio == null) {
                showError(view, "Silakan pilih peran Anda!");
                return;
            }
            String selectedRole = selectedRoleRadio.getText();

            // Registrasi Sukses
            System.out.println("Registrasi berhasil untuk: " + email + " sebagai " + selectedRole);
            viewManager.showLoginView(); // Kembali ke halaman login
        });
    }

    // Metode helper untuk menampilkan error agar lebih rapi
    private void showError(RegisterView view, String message) {
        if (view.getErrorLabel() != null) {
            view.getErrorLabel().setText(message);
            view.getErrorLabel().setVisible(true);
        }
    }
}
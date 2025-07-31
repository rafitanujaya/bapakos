package Controller.Register;

import Controller.ViewManager;
import View.Register.RegisterView;
import javafx.beans.binding.BooleanBinding;

import java.util.ArrayList;
import java.util.List;

public class RegisterController {

    // Simulasi database untuk mengecek duplikasi email
    private List<String> existingEmails = new ArrayList<>();

    public RegisterController(RegisterView view, ViewManager viewManager) {
        // Isi database simulasi
        existingEmails.add("user@gmail.com");

        // Sembunyikan error label pada awalnya
        view.getErrorLabel().setVisible(false);

        // --- LOGIKA 1: BUAT KOTAK PASSWORD MERAH JIKA BERBEDA ---
        // Tambahkan listener ke kedua field password
        view.getPassTxt().textProperty().addListener((obs, oldVal, newVal) -> validatePasswordMatch(view));
        view.getConfirmPassTxt().textProperty().addListener((obs, oldVal, newVal) -> validatePasswordMatch(view));

        // --- LOGIKA 2: TOMBOL REGISTER TIDAK BISA DIKLIK ---
        // Buat binding untuk setiap kondisi
        BooleanBinding isEmailEmpty = view.getEmailTxt().textProperty().isEmpty();
        BooleanBinding isPassEmpty = view.getPassTxt().textProperty().isEmpty();
        BooleanBinding isConfirmPassEmpty = view.getConfirmPassTxt().textProperty().isEmpty();
        BooleanBinding isTermsNotChecked = view.getTermsCheckBox().selectedProperty().not();

        // Gabungkan semua kondisi. Tombol akan nonaktif jika salah satu kondisi true.
        view.getRegisterBtn().disableProperty().bind(
                isEmailEmpty.or(isPassEmpty).or(isConfirmPassEmpty).or(isTermsNotChecked)
        );

        // --- LOGIKA 3 & 4: SAAT TOMBOL REGISTER DIKLIK ---
        view.getRegisterBtn().setOnAction(event -> {
            String email = view.getEmailTxt().getText();
            String password = view.getPassTxt().getText();
            String confirmPassword = view.getConfirmPassTxt().getText();

            // Cek 1: Password harus cocok (sebagai pengaman tambahan)
            if (!password.equals(confirmPassword)) {
                view.getErrorLabel().setText("Password dan konfirmasi tidak cocok!");
                view.getErrorLabel().setVisible(true);
                return; // Hentikan proses
            }

            // Cek 2: Cek duplikasi email
            if (existingEmails.contains(email)) {
                view.getErrorLabel().setText("Email ini sudah terdaftar!");
                view.getErrorLabel().setVisible(true);
            } else {
                // Register Sukses
                System.out.println("Registrasi berhasil untuk email: " + email);
                // Di dunia nyata, Anda akan menyimpan data ini ke database

                // Kembali ke halaman login
                viewManager.showLoginView();
            }
        });
    }

    /**
     * Metode untuk memvalidasi apakah password dan konfirmasi password cocok.
     * Jika tidak, akan menambahkan style class .error-field.
     */
    private void validatePasswordMatch(RegisterView view) {
        String password = view.getPassTxt().getText();
        String confirmPassword = view.getConfirmPassTxt().getText();

        // Hapus style error jika salah satu field kosong agar tidak merah saat baru diisi
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            view.getPassTxt().getStyleClass().remove("error-field");
            view.getConfirmPassTxt().getStyleClass().remove("error-field");
            return;
        }

        if (password.equals(confirmPassword)) {
            // Jika sama, hapus style error
            view.getPassTxt().getStyleClass().remove("error-field");
            view.getConfirmPassTxt().getStyleClass().remove("error-field");
        } else {
            // Jika berbeda, tambahkan style error
            if (!view.getPassTxt().getStyleClass().contains("error-field")) {
                view.getPassTxt().getStyleClass().add("error-field");
            }
            if (!view.getConfirmPassTxt().getStyleClass().contains("error-field")) {
                view.getConfirmPassTxt().getStyleClass().add("error-field");
            }
        }
    }
}
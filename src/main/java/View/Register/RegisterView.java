package View.Register;

import javafx.geometry.Insets; // <-- IMPORT BARU
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane; // <-- IMPORT BARU
import javafx.scene.layout.VBox;

public class RegisterView {
    private VBox root;
    private ImageView logoView;
    private Label titleLabel;
    private VBox formBox;
    private TextField emailTxt;
    private PasswordField passTxt;
    private PasswordField confirmPassTxt;
    private CheckBox termsCheckBox;
    private Button registerBtn;
    private Label errorLabel;

    public RegisterView() {
        root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getStyleClass().add("root");

        // Logo
        Image img = new Image(getClass().getResourceAsStream("/img/bapa-kos-icon-png.png"));
        logoView = new ImageView(img);
        logoView.setFitHeight(140);
        logoView.setFitWidth(140);

        // Judul
        titleLabel = new Label("Daftar Akun BapaKos");
        titleLabel.getStyleClass().add("title-label");

        // formBox
        formBox = new VBox(15);
        formBox.setMaxWidth(300);
        formBox.getStyleClass().add("form-box");

        // Input fields
        emailTxt = new TextField();
        emailTxt.setPromptText("Email");
        passTxt = new PasswordField();
        passTxt.setPromptText("Password");
        confirmPassTxt = new PasswordField();
        confirmPassTxt.setPromptText("Konfirmasi Password");

        // --- PERBAIKAN UTAMA DI SINI ---

        // 1. Buat CheckBox tanpa teks
        termsCheckBox = new CheckBox();

        // 2. Buat Label dengan teks panjang dan aktifkan wrap
        Label termsLabel = new Label("Dengan ini saya setuju sebagai pengguna BapaKos beserta syarat dan ketentuan");
        termsLabel.setWrapText(true);

        // 3. Gabungkan keduanya dalam sebuah BorderPane
        BorderPane termsBox = new BorderPane();
        termsBox.setLeft(termsCheckBox);
        termsBox.setCenter(termsLabel);
        BorderPane.setMargin(termsCheckBox, new Insets(0, 10, 0, 0)); // Jarak 10px di kanan checkbox

        // -----------------------------

        // Tombol Register
        registerBtn = new Button("Daftar");
        registerBtn.setMaxWidth(Double.MAX_VALUE);
        registerBtn.getStyleClass().add("sign-in-button");

        // Label Error
        errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");
        errorLabel.setVisible(false);

        Label emailLabel = new Label("Email");
        emailLabel.getStyleClass().add("input-label");
        Label passLabel = new Label("Password");
        passLabel.getStyleClass().add("input-label");
        Label confirmPassLabel = new Label("Konfirmasi Password");
        confirmPassLabel.getStyleClass().add("input-label");

        // Masukkan semua komponen form ke dalam formBox
        formBox.getChildren().addAll(
                emailLabel, emailTxt,
                passLabel, passTxt,
                confirmPassLabel, confirmPassTxt,
                termsBox, // <-- Masukkan BorderPane yang baru
                registerBtn,
                errorLabel
        );

        // Susun komponen akhir di root
        root.getChildren().addAll(
                logoView,
                titleLabel,
                formBox
        );
    }

    // ... (Metode Getter Anda tetap sama)
    public Parent getView() { return root; }
    public TextField getEmailTxt() { return emailTxt; }
    public PasswordField getPassTxt() { return passTxt; }
    public PasswordField getConfirmPassTxt() { return confirmPassTxt; }
    public CheckBox getTermsCheckBox() { return termsCheckBox; }
    public Button getRegisterBtn() { return registerBtn; }
    public Label getErrorLabel() { return errorLabel; }
}
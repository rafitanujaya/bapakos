package View;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class RegisterView {
    private VBox root;
    private ImageView logoView;
    private Label titleLabel;
    private VBox formBox;
    private TextField emailTxt;
    private PasswordField passTxt;
    private PasswordField confirmPassTxt; // Tambahan untuk konfirmasi password
    private CheckBox termsCheckBox;       // Checkbox baru
    private Button registerBtn;
    private Label errorLabel;

    public RegisterView() {
        // Wadah utama dengan layout vertikal
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

        // Form untuk input
        formBox = new VBox(10);
        formBox.setMaxWidth(300);
        formBox.getStyleClass().add("form-box");

        // Input fields
        emailTxt = new TextField();
        emailTxt.setPromptText("Email");

        passTxt = new PasswordField();
        passTxt.setPromptText("Password");

        // Konfirmasi password, praktik yang baik untuk registrasi
        confirmPassTxt = new PasswordField();
        confirmPassTxt.setPromptText("Konfirmasi Password");

        errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label"); // Pakai style yang sama
        errorLabel.setVisible(false); // Sembunyikan awalnya

        formBox.getChildren().addAll(
                new Label("Email"), emailTxt,
                new Label("Password"), passTxt,
                new Label("Konfirmasi Password"), confirmPassTxt
        );

        // Checkbox untuk syarat dan ketentuan
        termsCheckBox = new CheckBox("Dengan ini saya setuju sebagai pengguna BapaKos beserta syarat dan ketentuan");
        termsCheckBox.setWrapText(true); // Agar teks panjang bisa turun baris
        termsCheckBox.setMaxWidth(300);

        // Tombol Register
        registerBtn = new Button("Daftar");
        registerBtn.setMaxWidth(300);
        registerBtn.getStyleClass().add("sign-in-button"); // Bisa pakai gaya yang sama dengan tombol login

        // Susun semua komponen di root VBox
        root.getChildren().addAll(
                logoView,
                titleLabel,
                formBox,
                errorLabel,
                termsCheckBox, // Checkbox di atas tombol
                registerBtn
        );
    }


    // Getter untuk diakses oleh Controller
    public Parent getView() { return root; }
    public TextField getEmailTxt() { return emailTxt; }
    public PasswordField getPassTxt() { return passTxt; }
    public PasswordField getConfirmPassTxt() { return confirmPassTxt; }
    public CheckBox getTermsCheckBox() { return termsCheckBox; }
    public Button getRegisterBtn() { return registerBtn; }
    public Label getErrorLabel() { return errorLabel; }
}
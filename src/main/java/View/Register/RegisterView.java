package View.Register;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class RegisterView {

    private HBox root;
    private TextField emailTxt;
    private PasswordField passTxt;
    private PasswordField confirmPassTxt;
    private ToggleGroup roleToggleGroup;
    private RadioButton pemilikRadio;
    private RadioButton penyewaRadio;
    private CheckBox termsCheckBox;
    private Button registerBtn;
    private Label loginLabel;
    private Label errorLabel;

    public RegisterView() {
        // --- Panel Kiri (Gambar) ---
        ImageView imageView = new ImageView(new Image("/img/login-pict.jpg"));
        imageView.setFitWidth(800);
        imageView.setFitHeight(800);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        VBox leftPanel = new VBox(imageView);
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPadding(new Insets(20));

        // --- Panel Kanan (Form) ---
        VBox rightPanel = new VBox(25);
        rightPanel.setAlignment(Pos.CENTER);

        VBox formContainer = new VBox(15);
        formContainer.setAlignment(Pos.CENTER_LEFT);
        formContainer.setMaxWidth(350);

        // Header
        ImageView logoView = new ImageView(new Image("/img/bapa-kos-icon-png.png"));
        logoView.setFitHeight(130);
        logoView.setFitWidth(130);
        Label titleLabel = new Label("Daftar Akun BapaKos");
        titleLabel.getStyleClass().add("login-title");
        VBox headerBox = new VBox(10, logoView, titleLabel);
        headerBox.setAlignment(Pos.CENTER);

        // --- PERBAIKAN DI SINI: Inisialisasi TextField dan PasswordField ---
        emailTxt = new TextField();
        emailTxt.setPromptText("Email");

        passTxt = new PasswordField();
        passTxt.setPromptText("Password");

        confirmPassTxt = new PasswordField();
        confirmPassTxt.setPromptText("Konfirmasi Password");
        // ---------------------------------------------------------------

        // Buat label-label
        Label emailLabel = new Label("Email");
        emailLabel.getStyleClass().add("input-label");
        Label passLabel = new Label("Password");
        passLabel.getStyleClass().add("input-label");
        Label confirmPassLabel = new Label("Konfirmasi Password");
        confirmPassLabel.getStyleClass().add("input-label");
        Label sebagaiLabel = new Label("Daftar Sebagai");
        sebagaiLabel.getStyleClass().add("input-label");

        // Radio Buttons
        roleToggleGroup = new ToggleGroup();
        pemilikRadio = new RadioButton("Pemilik Kos");
        pemilikRadio.getStyleClass().add("role-radio");
        pemilikRadio.setToggleGroup(roleToggleGroup);
        penyewaRadio = new RadioButton("Penyewa Kos");
        penyewaRadio.getStyleClass().add("role-radio");
        penyewaRadio.setToggleGroup(roleToggleGroup);
        penyewaRadio.setSelected(true);
        HBox roleBox = new HBox(20, pemilikRadio, penyewaRadio);

        // Terms and Conditions
        termsCheckBox = new CheckBox();
        Label termsLabel = new Label("Saya setuju dengan syarat dan ketentuan yang berlaku.");
        termsLabel.setWrapText(true);
        termsCheckBox.getStyleClass().add("role-checkbox");
        BorderPane termsBox = new BorderPane();
        termsBox.setLeft(termsCheckBox);
        termsBox.setCenter(termsLabel);
        BorderPane.setMargin(termsCheckBox, new Insets(0, 10, 0, 0));

        // Tombol Daftar
        registerBtn = new Button("Daftar");
        registerBtn.setMaxWidth(Double.MAX_VALUE);
        registerBtn.getStyleClass().add("login-button");

        // Label Error
        errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");
        errorLabel.setVisible(false);

        // Link ke halaman Login
        Label sudahPunyaAkun = new Label("Sudah punya akun? ");
        sudahPunyaAkun.getStyleClass().add("sub-text");
        loginLabel = new Label("Masuk");
        loginLabel.getStyleClass().add("register-link");
        HBox loginBox = new HBox(sudahPunyaAkun, loginLabel);
        loginBox.setAlignment(Pos.CENTER_LEFT);

        // Masukkan semua elemen ke formContainer
        formContainer.getChildren().addAll(
                emailLabel, emailTxt,
                passLabel, passTxt,
                confirmPassLabel, confirmPassTxt,
                sebagaiLabel, roleBox,
                termsBox,
                errorLabel,
                registerBtn,
                loginBox
        );

        rightPanel.setAlignment(Pos.CENTER);
        rightPanel.getChildren().addAll(headerBox, formContainer);

        // Gabungkan Semua
        root = new HBox(leftPanel, rightPanel);
        root.getStyleClass().add("login-root");
        leftPanel.prefWidthProperty().bind(root.widthProperty().multiply(0.6));
        rightPanel.prefWidthProperty().bind(root.widthProperty().multiply(0.4));
    }

    // ... (Metode Getter Anda tetap sama)
    public Parent getView() { return root; }
    public TextField getEmailTxt() { return emailTxt; }
    public PasswordField getPassTxt() { return passTxt; }
    public PasswordField getConfirmPassTxt() { return confirmPassTxt; }
    public ToggleGroup getRoleToggleGroup() { return roleToggleGroup; }
    public RadioButton getPemilikRadio() { return pemilikRadio; }
    public RadioButton getPenyewaRadio() { return penyewaRadio; }
    public CheckBox getTermsCheckBox() { return termsCheckBox; }
    public Button getRegisterBtn() { return registerBtn; }
    public Label getLoginLabel() { return loginLabel; }
    public Label getErrorLabel() { return errorLabel; }
}
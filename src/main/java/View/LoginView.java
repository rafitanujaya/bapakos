package View;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// 1. HAPUS "extends Application"
public class LoginView {
    private VBox root;
    private ImageView logoView;
    private Label titleLabel;
    private VBox formBox;
    private Label userLabel;
    private TextField userTxt;
    private Label passLabel;
    private PasswordField passTxt;
    private Button loginBtn;
    private Label orLabel;
    private Button registerButton;
    private Label errorLabel;
    private HBox roleSelectionBox;
    private ToggleGroup roleToggleGroup;
    private RadioButton pemilikRadio;
    private RadioButton penyewaRadio;

    // 2. BUAT CONSTRUCTOR dan pindahkan semua kode dari start() ke sini
    public LoginView() {
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
        titleLabel = new Label("Masuk ke BapaKos");
        titleLabel.getStyleClass().add("title-label");

        // Form untuk input
        formBox = new VBox(10);
        formBox.setMaxWidth(300);
        formBox.getStyleClass().add("form-box");

        // Komponen form
        userLabel = new Label("Email");
        userLabel.getStyleClass().add("input-label");
        userTxt = new TextField();
        userTxt.setPromptText("Email");

        passLabel = new Label("Password");
        passLabel.getStyleClass().add("input-label");
        passTxt = new PasswordField();
        passTxt.setPromptText("Password");

        formBox.getChildren().addAll(userLabel, userTxt, passLabel, passTxt);

        // Tombol Login
        loginBtn = new Button("Login");
        loginBtn.setMaxWidth(300);
        loginBtn.getStyleClass().add("sign-in-button");

        // Label Error
        errorLabel = new Label(); // Pastikan ini dibuat!
        errorLabel.getStyleClass().add("error-label");

        // Radio Buttons
        roleToggleGroup = new ToggleGroup();
        pemilikRadio = new RadioButton("Pemilik Kos");
        pemilikRadio.setToggleGroup(roleToggleGroup);
        pemilikRadio.getStyleClass().add("role-radio");
        penyewaRadio = new RadioButton("Penyewa Kos");
        penyewaRadio.setToggleGroup(roleToggleGroup);
        penyewaRadio.getStyleClass().add("role-radio");
        penyewaRadio.setSelected(true);

        roleSelectionBox = new HBox(25);
        roleSelectionBox.setAlignment(Pos.CENTER);
        roleSelectionBox.getChildren().addAll(pemilikRadio, penyewaRadio);

        orLabel = new Label("atau");

        registerButton = new Button("Daftar");
        registerButton.setMaxWidth(300);
        registerButton.getStyleClass().add("register-button");

        // Susun semua komponen di root VBox
        root.getChildren().addAll(
                logoView,
                titleLabel,
                formBox,
                roleSelectionBox,
                loginBtn,
                orLabel,
                registerButton,
                errorLabel
        );
    }

    // 3. Hapus metode start() yang lama

    // Metode Getter tetap sama, sekarang akan mengembalikan objek yang sudah dibuat
    public Parent getView() { return root; }
    public ImageView getLogoView() { return logoView; }
    public Label getTitleLabel() { return titleLabel; }
    public VBox getFormBox() { return formBox; }
    public Label getUserLabel() { return userLabel; }
    public TextField getUserTxt() { return userTxt; }
    public Label getPassLabel() { return passLabel; }
    public PasswordField getPassTxt() { return passTxt; }
    public Button getLoginBtn() { return loginBtn; }
    public Label getErrorLabel() { return errorLabel; }
    public Label getOrLabel() { return orLabel; }
    public Button getRegisterButton() { return registerButton; }
    public ToggleGroup getRoleToggleGroup() { return roleToggleGroup; }
    public RadioButton getPemilikRadio() { return pemilikRadio; }
    public RadioButton getPenyewaRadio() { return penyewaRadio; }
}
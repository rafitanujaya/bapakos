package View.Login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class LoginView {

    private HBox root;
    private TextField userTxt;
    private PasswordField passTxt;
    private CheckBox pemilikCheckBox;
    private Button loginBtn;
    private Label registerLabel;

    public LoginView() {
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

        // --- Header ---
        ImageView logoView = new ImageView(new Image("/img/bapa-kos-icon-png.png"));
        logoView.setFitHeight(130);
        logoView.setFitWidth(130);
        Label titleLabel = new Label("Welcome To BapaKos");
        titleLabel.getStyleClass().add("login-title");
        VBox headerBox = new VBox(10, logoView, titleLabel);
        headerBox.setAlignment(Pos.CENTER);

        // --- Form ---
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER_LEFT);
        formBox.setMaxWidth(350);

        // Field Username
        Label userLabel = new Label("Username");
        userLabel.getStyleClass().add("input-label");
        userTxt = new TextField();
        userTxt.setPromptText("Username");

        // Field Password
        Label passLabel = new Label("Password");
        passLabel.getStyleClass().add("input-label");
        passTxt = new PasswordField();
        passTxt.setPromptText("••••••••");

        // Checkbox peran
        pemilikCheckBox = new CheckBox("Property Owner");
        pemilikCheckBox.getStyleClass().add("role-checkbox");

        // Tombol Login
        loginBtn = new Button("Login");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.getStyleClass().add("login-button");

        // Link Register
        Label belumPunyaAkun = new Label("Don't have an account? ");
        registerLabel = new Label("Register");
        registerLabel.getStyleClass().add("register-link");
        HBox registerBox = new HBox(belumPunyaAkun, registerLabel);
        registerBox.setAlignment(Pos.CENTER_LEFT);
        registerBox.getStyleClass().add("register-text-container");

        // Masukkan elemen ke dalam formBox
        formBox.getChildren().addAll(
                userLabel, userTxt,
                passLabel, passTxt,
                pemilikCheckBox,
                loginBtn,
                registerBox
        );

        // Gabungkan header dan form
        rightPanel.getChildren().addAll(headerBox, formBox);

        // Gabungkan semua panel
        root = new HBox(leftPanel, rightPanel);
        root.getStyleClass().add("login-root");
        leftPanel.prefWidthProperty().bind(root.widthProperty().multiply(0.6));
        rightPanel.prefWidthProperty().bind(root.widthProperty().multiply(0.4));
    }

    public Parent getView() { return root; }
    public TextField getUserTxt() { return userTxt; }
    public PasswordField getPassTxt() { return passTxt; }
    public CheckBox getPemilikCheckBox() { return pemilikCheckBox; }
    public Button getLoginBtn() { return loginBtn; }
    public Label getRegisterLabel() { return registerLabel; }
}

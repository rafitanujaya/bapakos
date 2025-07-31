package Controller; // Atau package yang sesuai

import Controller.Admin.AdminPageController;
import Controller.Login.LoginController;
import Controller.Register.RegisterController;
import View.Login.LoginView;
import View.Register.RegisterView;
import View.User.UserDashboardView;
import View.Admin.AdminPageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewManager {

    private final Stage primaryStage;
    private Scene mainScene;
    private Parent loginRoot;
    private Parent registerRoot;
    private Parent adminDashboardRoot;
    private Parent userDashboardRoot;

    public ViewManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginView() {
        // Cek jika view belum pernah dibuat
        if (loginRoot == null) {
            LoginView loginView = new LoginView();
            new LoginController(loginView, this);

            // Bungkus dengan StackPane untuk latar belakang
            StackPane background = new StackPane(loginView.getView());
            background.getStyleClass().add("scene-background"); // Gunakan style class
            loginRoot = background;
        }

        if (mainScene == null) {
            mainScene = new Scene(loginRoot, 1220, 720);
            mainScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            primaryStage.setScene(mainScene);
        } else {
            mainScene.setRoot(loginRoot);
        }

        // Binding tetap diperlukan untuk view yang baru dibuat
        if (loginRoot instanceof Region) {
            ((Region) loginRoot).prefWidthProperty().bind(mainScene.widthProperty());
            ((Region) loginRoot).prefHeightProperty().bind(mainScene.heightProperty());
        }

        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    /**
     * Menampilkan view register dengan mengganti konten scene yang ada.
     */
    public void showRegisterView() {
        // Cek jika view belum pernah dibuat
        if (registerRoot == null) {
            RegisterView registerView = new RegisterView();
            new RegisterController(registerView, this);

            StackPane background = new StackPane(registerView.getView());
            background.getStyleClass().add("scene-background"); // Gunakan style class
            registerRoot = background;

            // Binding hanya saat pertama kali dibuat
            if (registerRoot instanceof Region) {
                ((Region) registerRoot).prefWidthProperty().bind(mainScene.widthProperty());
                ((Region) registerRoot).prefHeightProperty().bind(mainScene.heightProperty());
            }
        }

        mainScene.setRoot(registerRoot); // Tampilkan view yang sudah ada
        primaryStage.setTitle("Register");
    }

    public void showAdminDashboard() {
        AdminPageView adminView = new AdminPageView();
        new AdminPageController(adminView, this);

        Parent adminRoot = adminView.getView();
        mainScene.setRoot(adminRoot);

        primaryStage.setTitle("Dashboard Pemilik Kos");
    }

    public void showUserDashboard() {
        UserDashboardView userView = new UserDashboardView();
        // new UserDashboardController(userView, this); // Jika perlu controller
        Parent userRoot = userView.getView();
        mainScene.setRoot(userRoot);
        primaryStage.setTitle("Dashboard Penyewa Kos");
    }
}
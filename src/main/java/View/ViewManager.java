package View;

import View.Controller.Admin.AdminPageController;
import View.Controller.LoginController;
import View.Controller.RegisterController;
import View.Controller.User.UserDashboardController;
import View.Login.LoginView;
import View.Register.RegisterView;
import View.User.UserDashboardView;
import View.User.UserPageView;
import View.Admin.AdminPageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ViewManager {

    private final Stage primaryStage;
    private Scene mainScene;

    // Field untuk menyimpan view yang sudah dibuat
    private Parent loginRoot;
    private Parent registerRoot;
    private Parent adminDashboardRoot;
    private Parent userDashboardRoot;

    public ViewManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginView() {
        if (loginRoot == null) {
            LoginView loginView = new LoginView();
            new LoginController(loginView, this);
            StackPane background = new StackPane(loginView.getView());
            background.getStyleClass().add("scene-background");
            loginRoot = background;
        }

        if (mainScene == null) {
            mainScene = new Scene(loginRoot, 1220, 720);
            mainScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            primaryStage.setScene(mainScene);
        } else {
            mainScene.setRoot(loginRoot);
        }

        if (loginRoot instanceof Region) {
            ((Region) loginRoot).prefWidthProperty().bind(mainScene.widthProperty());
            ((Region) loginRoot).prefHeightProperty().bind(mainScene.heightProperty());
        }

        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    public void showRegisterView() {
        if (registerRoot == null) {
            RegisterView registerView = new RegisterView();
            new RegisterController(registerView, this);
            StackPane background = new StackPane(registerView.getView());
            background.getStyleClass().add("scene-background");
            registerRoot = background;

            if (registerRoot instanceof Region) {
                ((Region) registerRoot).prefWidthProperty().bind(mainScene.widthProperty());
                ((Region) registerRoot).prefHeightProperty().bind(mainScene.heightProperty());
            }
        }
        mainScene.setRoot(registerRoot);
        primaryStage.setTitle("Register");
    }

    public void showAdminDashboard() {
        // --- PERBAIKAN DI SINI ---

        // 1. Tambahkan lazy initialization
        if (adminDashboardRoot == null) {
            AdminPageView adminView = new AdminPageView();
            new AdminPageController(adminView, this);
            adminDashboardRoot = adminView.getView();

            // 2. Tambahkan binding ukuran agar view memenuhi layar
            if (adminDashboardRoot instanceof Region) {
                ((Region) adminDashboardRoot).prefWidthProperty().bind(mainScene.widthProperty());
                ((Region) adminDashboardRoot).prefHeightProperty().bind(mainScene.heightProperty());
            }
        }

        mainScene.setRoot(adminDashboardRoot);
        primaryStage.setTitle("Dashboard Pemilik Kos");
    }

    public void showUserDashboard() {
        // 1. Tampilkan bingkai utamanya dulu (UserPageView)
        UserPageView userPageView = new UserPageView();
        // Anda perlu controller untuk UserPageView di sini
        // new UserPageController(userPageView, this);
        mainScene.setRoot(userPageView.getView());

        // 2. Buat konten dashboard
        UserDashboardView dashboardView = new UserDashboardView();
        new UserDashboardController(dashboardView);

        // 3. Masukkan konten dashboard ke tengah bingkai UserPageView
        userPageView.getRootPane().setCenter(dashboardView.getView());

        primaryStage.setTitle("Dashboard Penyewa Kos");
    }
}
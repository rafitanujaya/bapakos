package Controller; // Atau package yang sesuai

import View.LoginView;
import View.RegisterView;
import View.AdminDashboardView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

    private final Stage primaryStage;
    private Scene mainScene;

    public ViewManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showLoginView() {
        // 1. Buat instance dari kelas View
        LoginView loginView = new LoginView();

        // 2. Buat instance Controller, lalu berikan View & ViewManager
        new LoginController(loginView, this);

        // 3. Ambil layout Parent dari objek view
        // Disarankan untuk mengubah nama metode getRoot() menjadi getView() di LoginView
        Parent loginRoot = loginView.getView();

        // 4. Buat Scene baru HANYA jika belum ada, lalu tampilkan
        if (mainScene == null) {
            mainScene = new Scene(loginRoot, 1220, 720);
            // Hubungkan CSS sekali saja saat scene dibuat
            mainScene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            primaryStage.setScene(mainScene);
        } else {
            mainScene.setRoot(loginRoot);
        }

        primaryStage.setTitle("Login");
        primaryStage.show();
    }


    /**
     * Menampilkan view register dengan mengganti konten scene yang ada.
     */
    public void showRegisterView() {
        RegisterView registerView = new RegisterView();

        // PASTIKAN BARIS INI ADA DAN AKTIF
        new RegisterController(registerView, this);

        Parent registerRoot = registerView.getView();
        mainScene.setRoot(registerRoot);
        primaryStage.setTitle("Register");
    }

    public void showAdminDashboard() {
        AdminDashboardView adminView = new AdminDashboardView();
        new AdminDashboardController(adminView, this);
        Parent adminRoot = adminView.getView();
        mainScene.setRoot(adminRoot);
        primaryStage.setTitle("Dashboard Pemilik Kos");
    }

//    public void showUserDashboard() {
//        UserDashboardView userView = new UserDashboardView();
//        // new UserDashboardController(userView, this); // Jika perlu controller
//        Parent userRoot = userView.getView();
//        mainScene.setRoot(userRoot);
//        primaryStage.setTitle("Dashboard Penyewa Kos");
//    }
}
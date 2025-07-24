package Controller; // Atau package yang sesuai

import View.HomepageView;
import View.LoginView;
import View.RegisterView;
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
     * Menampilkan view homepage dengan mengganti konten scene yang ada.
     */
    public void showHomepageView() {
        // 1. Buat instance view untuk homepage
        HomepageView homepageView = new HomepageView();
        // Jika ada controller, buat di sini: new HomepageController(homepageView, this);

        // 2. Ambil layout dari view
        Parent homepageRoot = homepageView.getView();

        // 3. Ganti konten scene dan update judul
        mainScene.setRoot(homepageRoot);
        primaryStage.setTitle("Homepage");
    }

    /**
     * Menampilkan view register dengan mengganti konten scene yang ada.
     */
    public void showRegisterView() {
        // 1. Buat instance view untuk register
        RegisterView registerView = new RegisterView();
        // Jika ada controller, buat di sini: new RegisterController(registerView, this);

        // 2. Ambil layout dari view
        Parent registerRoot = registerView.getView();

        // 3. Ganti konten scene dan update judul
        mainScene.setRoot(registerRoot);
        primaryStage.setTitle("Register");
    }
}
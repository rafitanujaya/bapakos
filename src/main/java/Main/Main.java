package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import Controller.ViewManager;
/**
 * Kelas utama yang menjalankan seluruh aplikasi JavaFX.
 */
public class Main extends Application {

    /**
     * Metode ini adalah titik masuk utama untuk semua aplikasi JavaFX.
     * @param primaryStage Panggung utama (jendela aplikasi) yang disediakan oleh JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        // 1. Buat instance dari ViewManager dan berikan "panggung utama" kepadanya.
        ViewManager viewManager = new ViewManager(primaryStage);

        // 2. Perintahkan ViewManager untuk menampilkan tampilan awal (login view).
        viewManager.showLoginView();
    }

    /**
     * Metode main() standar yang digunakan untuk meluncurkan aplikasi.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
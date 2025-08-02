package Main;

import Config.DBConfig;
import Controller.UserController;
import Dao.UserDAO;
import Service.UserService;
import View.Login.LoginView;
import View.ViewManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManager viewManager = new ViewManager(primaryStage);
        viewManager.showLoginView();
    }

    public static void main(String[] args) {
        try {
            // 1. Coba buat koneksi
            DBConfig dbConfig = new DBConfig();
            Connection connection = dbConfig.getConnection();

            // 2. Jika berhasil, cetak pesan sukses
            System.out.println("Database terhubung!");

            // 3. Tutup koneksi setelah selesai mengecek
            connection.close();

        } catch (SQLException e) {
            // 4. Jika gagal, cetak pesan error
            System.err.println("Gagal terhubung ke database. Cek konfigurasi Anda.");
            e.printStackTrace();
        }
        launch(args);
    }
}

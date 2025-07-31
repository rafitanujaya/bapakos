package Main;

import Config.DBConfig;
import Controller.UserController;
import Dao.UserDAO;
import Service.UserService;
import View.Login.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginView loginView = new LoginView();

        Scene scene = new Scene(loginView.getView(), 1220, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("hallo");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

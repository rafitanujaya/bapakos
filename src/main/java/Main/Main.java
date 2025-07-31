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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewManager viewManager = new ViewManager(primaryStage);
        viewManager.showLoginView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

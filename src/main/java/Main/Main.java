package Main;

import Config.DBConfig;
import Controller.UserController;
import Dao.UserDAO;
import Service.UserService;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = new DBConfig().getConnection();

            UserDAO userDAO = new UserDAO(conn);
            UserService userService = new UserService(userDAO);
            UserController userController = new UserController(userService);

            // Handle Register Done
//            userController.handleRegister("rafi", "rafi", "penyewa");
            // Handle Login
//            System.out.println(userService.login("rafi", "rafi"));

            System.out.println("Connected to database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
package Controller;

import Model.UserModel;
import Service.UserService;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean handleRegister(String username, String password, String role) {
        try {
            UserModel.Role roleEnum = role.equalsIgnoreCase("penyewa") ? UserModel.Role.PENYEWA : UserModel.Role.USERS;
            return userService.register(username, password, roleEnum);
        } catch (Exception e) {
            System.out.println("Gagal mendaftarkan pengguna: " + e.getMessage());
            return false;
        }
    }

    public boolean handleLogin(String username, String password) {
        try {
            return userService.login(username, password);
        } catch (Exception e) {
            System.out.println("Gagal login pengguna: " + e.getMessage());
            return false;
        }
    }

}

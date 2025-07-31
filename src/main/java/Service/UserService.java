package Service;

import Dao.UserDAO;
import Model.UserModel;
import Session.Session;
import utils.PasswordUtils;

import java.sql.SQLException;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean register(String username, String password, UserModel.Role role) throws SQLException {
        if (userDAO.isUsernameExist(username)) {
            return false;
        }
        UserModel user = new UserModel();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(PasswordUtils.hashPassword(password));
        user.setRoles(role);

        return userDAO.register(user);
    }

    public boolean login(String username, String password) throws SQLException {
        UserModel user = userDAO.findByUsername(username);
        if (user == null || !PasswordUtils.checkPassword(password, user.getPassword())) {
            return false;
        }
        Session.set(user);
        return true;
    }
}

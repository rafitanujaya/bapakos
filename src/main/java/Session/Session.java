package Session;

import Model.UserModel;

public class Session {
    private static UserModel currentUser;

    public static void set(UserModel user) {
        currentUser = user;
    }

    public static UserModel get() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }
}

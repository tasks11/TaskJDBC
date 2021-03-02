package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        userService.createUsersTable();
        userService.saveUser("misha", "mol", (byte) 22);
        userService.saveUser("masha", "dav", (byte) 18);
        userService.saveUser("Dmitry", "rer", (byte) 30);
        userService.saveUser("gena", "sas", (byte) 45);
//        userService.getAllUsers();
//        userService.removeUserById(1);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Lora", "Adams", (byte) 33);
        userService.saveUser("Tom", "Jons", (byte) 2);
        userService.saveUser("Joe", "Abroms", (byte) 13);
        userService.saveUser("Neal", "Armstrong", (byte) 13);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

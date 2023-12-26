package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.getAllUsers();

        userService.saveUser("Ivan", "Ivanov", (byte) 14);
        userService.saveUser("Petr", "Petrov", (byte) 45);
        userService.saveUser("Semen", "Semenov", (byte) 27);
        userService.saveUser("Bob", "I", (byte) 3);
        userService.getAllUsers();

        userService.removeUserById(2);
        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.getAllUsers();

        userService.dropUsersTable();
    }
}

package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("Таблица удалена");
    }

    public void saveUser(String name, String lastName, Byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.printf("User с именем – %s добавлен в базу данных\n", name);
    }

    public void removeUserById(Long id) {
        userDao.removeUserById(id);
        System.out.printf("User с id = %s удален\n", id);
    }

    public List<User> getAllUsers() {
        List<User> usersList = userDao.getAllUsers();
        System.out.println(usersList);
        return usersList;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("Таблица очищена");
    }
}

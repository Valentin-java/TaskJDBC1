package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        userDao.createUsersTable();
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        userDao.cleanUsersTable();
    }
}

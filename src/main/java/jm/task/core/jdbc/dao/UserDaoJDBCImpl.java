package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        Connection conn = Util.getMySQLConnection();
        PreparedStatement cmd = null;
        try {
            String sql = "CREATE TABLE `dbtest`.`user`(`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci";
            cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } finally {
            cmd.close();
            conn.close();
        }
    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        Connection conn = Util.getMySQLConnection();
        PreparedStatement cmd = null;
        try {
            String sql = "DROP TABLE IF EXISTS `dbtest`.`user`";
            cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } finally {
            cmd.close();
            conn.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        Connection conn = Util.getMySQLConnection();
        PreparedStatement cmd = null;
        try {
            String sql = "INSERT INTO user (name, lastName, age) VALUES (?,?,?)";
            cmd = conn.prepareStatement(sql);
            cmd.setString(1, name);
            cmd.setString(2, lastName);
            cmd.setByte(3, age);

            if (cmd.executeUpdate() == 1) {
                System.out.println("User с именем – " + name + " добавлен в базу данных.");
            }
        } finally {
            cmd.close();
            conn.close();
        }

    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        Connection conn = Util.getMySQLConnection();
        PreparedStatement cmd = null;
        try {
            String sql = "DELETE FROM `user` WHERE id=?" ;
            cmd = conn.prepareStatement(sql);
            cmd.setLong(1, id);
            cmd.executeUpdate();
        } finally {
            cmd.close();
            conn.close();
        }
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        Connection conn = Util.getMySQLConnection();
        PreparedStatement cmd = null;
        ResultSet result = null;
        List<User> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user";
            cmd = conn.prepareStatement(sql);
            result = cmd.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                byte age = result.getByte("age");
                list.add(new User(name, lastName, age));
            }
        } finally {
            result.close();
            cmd.close();
            conn.close();
        }
        return list;
    }

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        Connection conn = Util.getMySQLConnection();
        PreparedStatement cmd = null;
        try {
            String sql = "TRUNCATE `dbtest`.`user`";
            cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } finally {
            cmd.close();
            conn.close();
        }
    }
}

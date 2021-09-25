package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserServiceImpl implements UserService {


    public UserServiceImpl() {
    }

    public void createUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            String sql = "CREATE TABLE `dbtest`.`user`(`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci";
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } catch (SQLException throwables) {

        }
        catch (ClassNotFoundException e) {

        }
    }

    public void dropUsersTable() {

        try (Connection conn = Util.getMySQLConnection()){
            String sql = "DROP TABLE `dbtest`.`user`";
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } catch (SQLException throwables) {

        }
        catch (ClassNotFoundException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getMySQLConnection()) {
            String sql = "INSERT INTO user (name, lastName, age) VALUES (?,?,?)";
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.setString(1, name);
            cmd.setString(2, lastName);
            cmd.setByte(3, age);

            if (cmd.executeUpdate() == 1) {
                System.out.println("User с именем – " + name + " добавлен в базу данных.");
            }

        } catch (SQLException throwables) {

        }
        catch (ClassNotFoundException e) {

        }

    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection()) {
            String sql = "DELETE FROM `user` WHERE id = " + id;
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } catch (SQLException throwables) {

        }
        catch (ClassNotFoundException e) {

        }
    }

    public List<User> getAllUsers() {
            List<User> list = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection()) {
            String sql = "SELECT * FROM user";
            PreparedStatement cmd = conn.prepareStatement(sql);
            ResultSet result = cmd.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                byte age = result.getByte("age");
                list.add(new User(name, lastName, age));
            }
        } catch (SQLException throwables) {

        }
        catch (ClassNotFoundException e) {

        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getMySQLConnection()) {
            String sql = "TRUNCATE `dbtest`.`user`";
            PreparedStatement cmd = conn.prepareStatement(sql);
            cmd.executeUpdate();
        } catch (SQLException throwables) {

        }
        catch (ClassNotFoundException e) {

        }
    }
}

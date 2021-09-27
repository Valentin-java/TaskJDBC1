package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "CREATE TABLE `dbtest`.`user`(`id` INT NOT NULL AUTO_INCREMENT, `name` VARCHAR(45) NULL, `lastName` VARCHAR(45) NULL, `age` INT NULL, PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COLLATE = utf8_unicode_ci";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User userId = (User) session.load(User.class, id);
            session.delete(userId);
            session.getTransaction().commit();

        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            Query query = session.createQuery(cq);
            List<User> userList = query.getResultList();
            return userList;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}

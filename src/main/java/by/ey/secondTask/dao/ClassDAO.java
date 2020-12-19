package by.ey.secondTask.dao;

import by.ey.secondTask.entity.Class;
import by.ey.secondTask.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ClassDAO {
    public void add(Class classBalance) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(classBalance);
        transaction.commit();
        session.close();
    }

    public List<Class> getAll(Integer fileID) throws SQLException {
        String hql = "FROM Class C WHERE file = "+ fileID + " ORDER BY C.id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);

        List<Class> accountGroups = query.list();
        transaction.commit();
        session.close();
        return accountGroups;
    }
}

package by.ey.secondTask.dao;

import by.ey.secondTask.entity.File;
import by.ey.secondTask.entity.FileDescription;
import by.ey.secondTask.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class FileDAO {
    public void add(File file) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(file);
        transaction.commit();
        session.close();
    }

    public List<File> getAll() throws SQLException {
        String hql = "FROM File F ORDER BY F.id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);

        List files = query.list();
        transaction.commit();
        session.close();
        return files;
    }

    public FileDescription getByFile(int fileDescriptionID) throws SQLException {
        String hql = "FROM FileDescription FD WHERE FD.id = :id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        query.setParameter("id", fileDescriptionID);

        return (FileDescription) query.uniqueResult();
    }
}

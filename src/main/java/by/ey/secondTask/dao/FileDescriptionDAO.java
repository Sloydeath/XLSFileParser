package by.ey.secondTask.dao;

import by.ey.secondTask.entity.File;
import by.ey.secondTask.entity.FileDescription;
import by.ey.secondTask.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class FileDescriptionDAO {
    public void add(FileDescription fileDescription) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(fileDescription);
        transaction.commit();
        session.close();
    }
}

package by.ey.secondTask.dao;

import by.ey.secondTask.entity.AccountGroup;
import by.ey.secondTask.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class AccountGroupDAO {
    public void add(AccountGroup accountGroup) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(accountGroup);
        transaction.commit();
        session.close();
    }

    public List<AccountGroup> getAll(int fileID) throws SQLException {
        String hql = "FROM AccountGroup AG WHERE AG.file.id = :fileID";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("fileID", fileID);

        List<AccountGroup> accountGroups = query.list();
        transaction.commit();
        session.close();
        return accountGroups;
    }
}

package by.ey.secondTask.dao;

import by.ey.secondTask.entity.Account;
import by.ey.secondTask.util.HibernateSessionFactoryUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.util.List;

public class AccountDAO {

    public void add(Account account) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(account);
        transaction.commit();
        session.close();
    }

    public List<Account> getAll(int fileID) throws SQLException {
        String hql = "FROM Account A WHERE file = " + fileID + " ORDER BY A.id";
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(hql);

        List<Account> accounts = query.list();
        transaction.commit();
        session.close();
        return accounts;
    }

/*    public List<Object[]> getSumByAccountsGroup(int fileID, int accountGroupID) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select sum(inputBalanceAsset), sum(inputBalanceLiability), sum(debit)," +
                " sum(credit), sum(outgoingBalanceAsset), sum(getOutgoingBalanceLiability) " +
                "from Account" +
                " where accountGroup = " + accountGroupID + "and file = " + fileID;

        Query sumQuery = session.createQuery(hql);
        List<Object[]> accountsSum = sumQuery.list();
        transaction.commit();
        session.close();
        return accountsSum;
    }*/

    public List<Account> getAccountsGroup(int fileID, int accountGroupID) throws SQLException {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Account where accountGroup = " + accountGroupID + " and file = " + fileID;

        Query sumQuery = session.createQuery(hql);
        List<Account> accountsSum = sumQuery.list();
        transaction.commit();
        session.close();
        return accountsSum;
    }

    public List<Account> getAccountsByClass(int fileID, int accountClassID) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Account where accountClass = " + accountClassID + " and file = " + fileID;

        Query sumQuery = session.createQuery(hql);
        List<Account> accounts = sumQuery.list();
        transaction.commit();
        session.close();
        return accounts;
    }
}

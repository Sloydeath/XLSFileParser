package by.ey.secondTask.service;

import by.ey.secondTask.dao.AccountGroupDAO;
import by.ey.secondTask.entity.AccountGroup;

import java.sql.SQLException;
import java.util.List;

public class AccountsGroupService {
    public List<AccountGroup>getAccountsGroups(int fileID) throws SQLException {
        AccountGroupDAO accountGroupDAO = new AccountGroupDAO();
        return accountGroupDAO.getAll(fileID);
    }
}

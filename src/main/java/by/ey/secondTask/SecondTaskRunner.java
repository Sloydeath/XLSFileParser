package by.ey.secondTask;

import by.ey.secondTask.dao.AccountDAO;
import by.ey.secondTask.dao.AccountGroupDAO;
import by.ey.secondTask.dao.FileDAO;
import by.ey.secondTask.entity.Account;
import by.ey.secondTask.entity.AccountGroup;
import by.ey.secondTask.entity.File;
import by.ey.secondTask.service.AccountsService;
import org.apache.poi.poifs.filesystem.Entry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SecondTaskRunner {
    public static void main(String[] args) throws IOException, SQLException {
        AccountGroupDAO accountGroupDAO = new AccountGroupDAO();
        AccountDAO accountDAO = new AccountDAO();
        AccountsService accountsService = new AccountsService();
        FileDAO fileDAO = new FileDAO();
        System.out.println(fileDAO.getByFile(1));
        //new AccountsService().readAccountsFromExcelAndWriteIntoDB("D:\\Andrew\\Work\\EY\\ОСВ для тренинга.xls");
       /* List<AccountGroup> accountGroupList = accountGroupDAO.getAll(1);
        for (AccountGroup accountGroup: accountGroupList) {
            System.out.println(accountGroup);
        }*/
       /* for (Map.Entry<Integer, Object[]> entry: accountsService.getSumsByAccountsGroups(1).entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (int i = 0; i < entry.getValue().length; i++) {
                System.out.print(entry.getValue()[i].toString() + " ");
            }
            System.out.println("");
        }*/
        /*for (Map.Entry<Integer, List<Double>> entry: accountsService.getSumsByAccountsClasses(1).entrySet()) {
            System.out.print(entry.getKey() + ": ");
            for (double element: entry.getValue()) {
                System.out.print(element + " ");
            }
            System.out.println("");
        }*/
/*        Map<Integer, List<Double>> map = accountsService.getSumsByAccountsClasses(1);
        System.out.println(map.get(1).get(0));*/
    }
} 

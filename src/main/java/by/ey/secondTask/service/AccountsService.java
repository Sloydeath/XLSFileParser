package by.ey.secondTask.service;

import by.ey.secondTask.dao.*;
import by.ey.secondTask.entity.Account;
import by.ey.secondTask.entity.AccountGroup;
import by.ey.secondTask.entity.Class;
import by.ey.secondTask.entity.FileDescription;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class AccountsService {
    //метод для чтения из эксель-файла и записи в БД
    public void readAccountsFromExcelAndWriteIntoDB(String fileName) throws IOException, SQLException {
        Class classBalance = null;
        AccountGroup accountGroup = null;
        FileDescription fileDescription = null;

        FileDAO fileDAO = new FileDAO();
        ClassDAO classDAO = new ClassDAO();
        AccountDAO accountDAO = new AccountDAO();
        AccountGroupDAO accountGroupDAO = new AccountGroupDAO();
        FileDescriptionDAO fileDescriptionDAO = new FileDescriptionDAO();

        File file = new File(fileName);
        HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet1");

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = myExcelSheet.iterator();

        //Чтение информации из шапки файла
        String[] descriptionString = new String[5];
        for (int i = 0; i < 5; i++) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            descriptionString[i] = cell.getStringCellValue();
            i++;
        }
        System.out.println(Arrays.toString(descriptionString));
        by.ey.secondTask.entity.File fileEntity = new by.ey.secondTask.entity.File(fileName);

        //Добавление в БД информации о файле
        fileDescription = new FileDescription();
        fileDescription.setBankName(descriptionString[0].trim());
        fileDescription.setPeriod(descriptionString[4].trim());
        fileDescriptionDAO.add(fileDescription);
        fileEntity.setDescription(fileDescription);
        fileDAO.add(fileEntity);

        //Пропуск 4 строк, чтобы перейти к 9 строке файла
        for (int i = 0; i < 4; i++) {
            rowIterator.next();
        }

        //Парсинг файла
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            // For each row, iterate through each columns
            Cell cell = cellIterator.next();
            CellType cellType = cell.getCellTypeEnum();
            if (CellType.STRING.equals(cellType) && cell.getStringCellValue().toLowerCase().contains("класс ")) {
                classBalance = new Class();
                classBalance.setId(Character.getNumericValue(cell.getStringCellValue().charAt(7)));
                classBalance.setName(cell.getStringCellValue());
                classBalance.setFile(fileEntity);
                classDAO.add(classBalance);
            }
            else if (CellType.STRING.equals(cell.getCellTypeEnum()) && cell.getStringCellValue().length() == 4) {
                String accountNumberString = cell.getStringCellValue();
                int count = 0;

                Account account = new Account();
                accountGroup = new AccountGroup();

                account.setId(Integer.parseInt(accountNumberString));
                accountGroup.setId(Integer.parseInt(accountNumberString.substring(0, 2)));
                accountGroup.setFile(fileEntity);
                accountGroupDAO.add(accountGroup);

                while (cellIterator.hasNext()) {
                    cell = cellIterator.next();
                    count++;
                    switch (count) {
                        case 1:
                            account.setInputBalanceAsset(cell.getNumericCellValue());
                            break;
                        case 2:
                            account.setInputBalanceLiability(cell.getNumericCellValue());
                            break;
                        case 3:
                            account.setDebit(cell.getNumericCellValue());
                            break;
                        case 4:
                            account.setCredit(cell.getNumericCellValue());
                            break;
                        case 5:
                            account.setOutgoingBalanceAsset(cell.getNumericCellValue());
                            break;
                        case 6:
                            account.setGetOutgoingBalanceLiability(cell.getNumericCellValue());
                            break;
                        default:
                    }
                }

                account.setAccountClass(classBalance);
                account.setAccountGroup(accountGroup);
                account.setFile(fileEntity);
                accountDAO.add(account);
            }
        }
    }

    public List<Account> getAccountsFromFile(int fileID) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        return accountDAO.getAll(fileID);
    }

    //метод получения сумм по группам
    public Map<Integer, List<Double>> getSumsByAccountsGroups(int fileID) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        AccountGroupDAO accountGroupDAO = new AccountGroupDAO();
        List<AccountGroup> accountGroups = accountGroupDAO.getAll(fileID);
        Map<Integer, List<Double>> totalSumAccountsGroups = new HashMap<Integer, List<Double>>();

        for (AccountGroup accountGroup: accountGroups) {
            List<Account> accountsByGroup = accountDAO.getAccountsGroup(fileID, accountGroup.getId());
            double sumInputBalanceAssetByGroup = 0;
            double sumInputBalanceLiabilityByGroup = 0;
            double sumDebitByGroup = 0;
            double sumCreditByGroup = 0;
            double sumOutgoingBalanceAssetByGroup = 0;
            double sumOutgoingBalanceLiabilityByGroup = 0;

            for (Account account: accountsByGroup) {
                sumInputBalanceAssetByGroup += account.getInputBalanceAsset();
                sumInputBalanceLiabilityByGroup += account.getInputBalanceLiability();
                sumDebitByGroup += account.getDebit();
                sumCreditByGroup += account.getCredit();
                sumOutgoingBalanceAssetByGroup += account.getOutgoingBalanceAsset();
                sumOutgoingBalanceLiabilityByGroup += account.getGetOutgoingBalanceLiability();
            }
            List<Double> listOfGroupsSum;
            listOfGroupsSum = Arrays.asList(sumInputBalanceAssetByGroup, sumInputBalanceLiabilityByGroup,
                    sumDebitByGroup, sumCreditByGroup, sumOutgoingBalanceAssetByGroup,
                    sumOutgoingBalanceLiabilityByGroup);
            totalSumAccountsGroups.put(accountGroup.getId(), listOfGroupsSum);
        }
        return totalSumAccountsGroups;
    }

    //метод получения сумм по классам
    public Map<Integer, List<Double>> getSumsByAccountsClasses(int fileID) throws SQLException {
        AccountDAO accountDAO = new AccountDAO();
        ClassDAO classDAO = new ClassDAO();
        List<Class> classes = classDAO.getAll(fileID);
        Map<Integer, List<Double>> totalSumAccountsByClass = new HashMap<Integer, List<Double>>();

        for (Class accountClass: classes) {
            List<Account> accountsByClass = accountDAO.getAccountsByClass(fileID, accountClass.getId());
            double sumInputBalanceAssetByClass = 0;
            double sumInputBalanceLiabilityByClass = 0;
            double sumDebitByClass = 0;
            double sumCreditByClass = 0;
            double sumOutgoingBalanceAssetByClass = 0;
            double sumOutgoingBalanceLiabilityByClass = 0;

            for (Account account: accountsByClass) {
                sumInputBalanceAssetByClass += account.getInputBalanceAsset();
                sumInputBalanceLiabilityByClass += account.getInputBalanceLiability();
                sumDebitByClass += account.getDebit();
                sumCreditByClass += account.getCredit();
                sumOutgoingBalanceAssetByClass += account.getOutgoingBalanceAsset();
                sumOutgoingBalanceLiabilityByClass += account.getGetOutgoingBalanceLiability();
            }
            List<Double> listOfClassSum;
            listOfClassSum = Arrays.asList(sumInputBalanceAssetByClass, sumInputBalanceLiabilityByClass,
                    sumDebitByClass, sumCreditByClass, sumOutgoingBalanceAssetByClass,
                    sumOutgoingBalanceLiabilityByClass);
            totalSumAccountsByClass.put(accountClass.getId(), listOfClassSum);
        }
        return totalSumAccountsByClass;
    }

    //метод получения баланса
    public List<Double> getTotalSumAll(int fileID) throws SQLException {
        double sumInputBalanceAssetByClass = 0;
        double sumInputBalanceLiabilityByClass = 0;
        double sumDebitByClass = 0;
        double sumCreditByClass = 0;
        double sumOutgoingBalanceAssetByClass = 0;
        double sumOutgoingBalanceLiabilityByClass = 0;
        for (Map.Entry<Integer, List<Double>> entry: getSumsByAccountsClasses(fileID).entrySet()) {
            sumInputBalanceAssetByClass += entry.getValue().get(0);
            sumInputBalanceLiabilityByClass += entry.getValue().get(1);
            sumDebitByClass += entry.getValue().get(2);
            sumCreditByClass += entry.getValue().get(3);
            sumOutgoingBalanceAssetByClass += entry.getValue().get(4);
            sumOutgoingBalanceLiabilityByClass += entry.getValue().get(5);
        }
        List<Double> totalSum = Arrays.asList(sumInputBalanceAssetByClass, sumInputBalanceLiabilityByClass,
                sumDebitByClass, sumCreditByClass, sumOutgoingBalanceAssetByClass,
                sumOutgoingBalanceLiabilityByClass);
        return totalSum;
    }
}
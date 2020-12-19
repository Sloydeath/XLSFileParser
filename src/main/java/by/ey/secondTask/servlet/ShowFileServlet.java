package by.ey.secondTask.servlet;

import by.ey.secondTask.entity.*;
import by.ey.secondTask.entity.Class;
import by.ey.secondTask.service.AccountsGroupService;
import by.ey.secondTask.service.AccountsService;
import by.ey.secondTask.service.ClassService;
import by.ey.secondTask.service.FileService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/content" })

public class ShowFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountsService accountsService = new AccountsService();
        AccountsGroupService accountsGroupService = new AccountsGroupService();
        ClassService classService = new ClassService();
        FileService fileService = new FileService();
        FileDescription fileDescription = null;
        List<Account> accounts = null;
        List<Class> classes = null;
        List<AccountGroup> accountGroups = null;
        Map<Integer, List<Double>> totalSumAccountsByClass = null;
        Map<Integer, List<Double>> totalSumAccountsGroups = null;
        List<Double> totalSumAccountsWholeFile = null;

        //получение информации о счетах из базы данных
        try {
            fileDescription = fileService.getFileDescription(1);
            accounts = accountsService.getAccountsFromFile(1);
            classes = classService.getAccountsClasses(1);
            accountGroups = accountsGroupService.getAccountsGroups(1);
            totalSumAccountsByClass = accountsService.getSumsByAccountsClasses(1);
            totalSumAccountsGroups = accountsService.getSumsByAccountsGroups(1);
            totalSumAccountsWholeFile = accountsService.getTotalSumAll(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //передача данных на клиент
        request.setAttribute("fileDescription", fileDescription);
        request.setAttribute("accounts", accounts);
        request.setAttribute("totalSumAccountsByClass", totalSumAccountsByClass);
        request.setAttribute("totalSumAccountsGroups", totalSumAccountsGroups);
        request.setAttribute("totalSumAccountsWholeFile", totalSumAccountsWholeFile);
        request.setAttribute("classes", classes);
        request.setAttribute("accountGroups", accountGroups);


        //переадресация
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/fileContentView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

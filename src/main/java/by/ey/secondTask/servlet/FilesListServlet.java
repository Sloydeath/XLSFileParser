package by.ey.secondTask.servlet;

import by.ey.secondTask.entity.File;
import by.ey.secondTask.service.FileService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/files" })

public class FilesListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FileService fileService = new FileService();
        List<File> fileList = null;

        //Получение списка файлов из бд
        try {
            fileList = fileService.getAllFilesNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("fileList", fileList);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/fileListview.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
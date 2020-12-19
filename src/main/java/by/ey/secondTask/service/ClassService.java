package by.ey.secondTask.service;

import by.ey.secondTask.dao.ClassDAO;
import by.ey.secondTask.entity.Class;

import java.sql.SQLException;
import java.util.List;

public class ClassService {
    public List<Class> getAccountsClasses(int fileID) throws SQLException {
        ClassDAO classDAO = new ClassDAO();
        return classDAO.getAll(fileID);
    }
}

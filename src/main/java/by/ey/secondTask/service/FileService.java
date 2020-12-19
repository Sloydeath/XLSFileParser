package by.ey.secondTask.service;

import by.ey.secondTask.dao.FileDAO;
import by.ey.secondTask.entity.File;
import by.ey.secondTask.entity.FileDescription;

import java.sql.SQLException;
import java.util.List;

public class FileService {
    public List<File> getAllFilesNames() throws SQLException {
        return new FileDAO().getAll();
    }

    public FileDescription getFileDescription(int fileID) throws SQLException {
        return new FileDAO().getByFile(fileID);
    }
}

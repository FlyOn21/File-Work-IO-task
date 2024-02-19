package app.utils;

import app.exceptions.DirectoryCreateException;

import java.io.File;
import java.util.Hashtable;

public class FilesGetUtil {
    public String path;

    public FilesGetUtil(String path) {
        this.path = path;
    }

    public String stringToPrintExistFiles() {
        StringBuilder listFilesBuffer = new StringBuilder();
        listFilesBuffer.append("------------ EXIST FILES --------------\n\n");
        directoryFiles().forEach(
                (fileName, filePath) -> listFilesBuffer
                                .append("=============================================\n")
                                .append("File name: ").append(fileName).append("\n")
                                .append("File path: ").append(filePath).append("\n")
                                .append("=============================================\n")
        );
        return listFilesBuffer.toString();
    }


    private String[] existsDirectoryFilesList(String path) {
        File directory = new File(path);
        String[] files = directory.list();
        return files != null ? files : new String[0];
    }

    private boolean isPathDirCheck(String path) {
        return new File(path).isDirectory();
    }

    private void addFilesFromDirectory(String path, Hashtable<String, String> filesTable) {
        for (String fileName : existsDirectoryFilesList(path)) {
            String fullPath = path + fileName;

            if (isPathDirCheck(fullPath)) {
                addFilesFromDirectory(fullPath, filesTable);
            } else {
                filesTable.put(fileName, fullPath);
            }
        }
    }

    public Hashtable<String, String> directoryFiles() throws DirectoryCreateException {

        Hashtable<String, String> filesTable = new Hashtable<>();
        addFilesFromDirectory(this.path, filesTable);
        return filesTable;
    }
}

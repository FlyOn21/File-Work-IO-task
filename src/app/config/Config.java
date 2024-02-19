package app.config;

import app.exceptions.DirectoryCreateException;

import java.io.File;
import java.nio.file.FileSystems;

public class Config {
    private static final String FOLDER_NAME = "files";
    public static final String BASE_DIRECTORY = makeBaseDirectory();

    public static final String FILE_EXTENSION = ".txt";

    private static String makeBaseDirectory() {
        String userDir = System.getProperty("user.dir");
        String systemSeparator = FileSystems.getDefault().getSeparator();
        String baseDirectory = userDir + systemSeparator + FOLDER_NAME + systemSeparator;

        File directory = new File(baseDirectory);
        if (!directory.exists()) {
            boolean wasSuccessful = directory.mkdirs();
            if (!wasSuccessful) {
                throw new DirectoryCreateException("Failed to create directory for files. Check permissions.");
            }
        }
        return baseDirectory;
    }
}

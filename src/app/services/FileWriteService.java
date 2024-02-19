package app.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import app.config.Config;

public class FileWriteService {
    public static void writeToFile(String writePath, String content, boolean isAdd) {
        String writeContent = content;
        if (isAdd) {
            String oldContent = FileReadService.readFile(writePath);
            writeContent = oldContent + content;
        }
        try (BufferedWriter buffWriter = new BufferedWriter(new FileWriter(writePath))) {
            buffWriter.write(writeContent);
            buffWriter.flush();
        } catch (IOException e) {
            System.out.println("Problem with write file... Check permission to write");
            e.printStackTrace();
        }
    }

    public static String makeNewFilePath(String newFileName) {
        return Config.BASE_DIRECTORY + newFileName;
    }

}

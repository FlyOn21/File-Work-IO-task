package app.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import app.config.Config;

public class FileReadService {
    public static String readFile(String path) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader currentFileBufferRider = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path)
                )
            )
        ) {
            String line;
            while ((line = currentFileBufferRider.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        } catch (IOException e) {
            System.out.println("Problem with read file... Maybe file is broken");
            e.printStackTrace();
        }
        return content.toString();
    }
}
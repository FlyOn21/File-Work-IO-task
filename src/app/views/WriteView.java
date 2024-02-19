package app.views;
import app.config.Config;
import app.services.FileWriteService;
import app.utils.FilesGetUtil;
import app.services.FileReadService;

import java.util.Hashtable;
import java.util.Scanner;


public class WriteView {
    private static final String writeMenu = """
            ------------ WRITE --------------
            Choice 1 => exist files list
            Choice 2 => write file, rewrite file or add additional data to existing file
            Choice 3 => to previous menu
            """;

    private static final String optionWriteMenu = """
            Choice r => rewrite file data
            Choice a => add new content data to file data
            """;

    public static void writeViewProcessing(Scanner scanner, FilesGetUtil existsFiles) {
        while (true) {
            System.out.println(writeMenu);
            System.out.print("Input your choice: ");
            String readChoice = scanner.nextLine();
            switch (readChoice){
                case "1":
                    String printFilesString = existsFiles.stringToPrintExistFiles();
                    System.out.println(printFilesString);
                    break;
                case "2":
                    String[] resultWrite = writeFileProcessing(scanner, existsFiles);
                    String writeFileContent = FileReadService.readFile(resultWrite[1]);
                    System.out.println("Write Success!");
                    System.out.println("File: " + resultWrite[0]);
                    System.out.println(writeFileContent);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }
    }

    private static String[] writeFileProcessing(Scanner scanner, FilesGetUtil existsFiles) {
        boolean flagAdd = false;
        System.out.println("Input write file name:");
        String fileName = scanner.nextLine();
        System.out.println("Input file content: ");
        String content = scanner.nextLine();
        String findFile = fileName + Config.FILE_EXTENSION;
        Hashtable<String, String> currentFilesHashTable= existsFiles.directoryFiles();
        String[] result = new String[2];
        result[0] = findFile;
        if (!currentFilesHashTable.containsKey(findFile)) {
            String newFilePath = FileWriteService.makeNewFilePath(findFile);
            FileWriteService.writeToFile(newFilePath ,content, flagAdd);
            result[1] = newFilePath;
        }
        else {
            System.out.println("File with that name is exist");
            String existFilePath = currentFilesHashTable.get(findFile);
            flagAdd = changeFlagAddState(scanner);
            FileWriteService.writeToFile(existFilePath ,content, flagAdd);
            result[1] = existFilePath;
        }
        return result;
    }


    private static boolean changeFlagAddState (Scanner scanner) {
        while (true) {
            System.out.println(optionWriteMenu);
            System.out.print("Input your choice: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "r":
                    return false;
                case "a":
                    return true;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        }
    }
}

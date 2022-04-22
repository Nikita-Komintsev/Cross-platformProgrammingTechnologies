package com.company;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Infornation();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine() + ".txt";
        File file = new File("C:/Users/nikit/JavaProjects/projectFiles/" + fileName);
        System.out.print("Введите строку для записи в файл: ");
        String inputText = scanner.nextLine();

        WriteFile(fileName, inputText);
        ReadFile(fileName);
        DeleteFile(file);
    }

    private static void Infornation() {

        FileSystemView fsv = FileSystemView.getFileSystemView();

        File[] f = File.listRoots();
        for (File file : f) {
            System.out.println("Drive: " + file);
            System.out.println("Display name: " + fsv.getSystemDisplayName(file));
            System.out.println("Readable: " + file.canRead());
            System.out.println("Writable: " + file.canWrite());
            System.out.println("Total space: " + file.getTotalSpace());
            System.out.println("Usable space: " + file.getUsableSpace());
        }
    }

    private static void DeleteFile(File file) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nУдалить файл? Y/N: ");
        String choice = scanner.nextLine();
        if (Objects.equals(choice, "Y") || Objects.equals(choice, "y")){
            if (file.delete()) {
                System.out.println(file.getName() + " deleted");
            } else {
                System.out.println(file.getName() + " not deleted");
            }
        }
    }

    private static void WriteFile(String fileName, String inputText) throws IOException {
        Files.write(Paths.get(fileName), inputText.getBytes());
        System.out.println("Текст записан в файл");
    }

    private static void ReadFile(String fileName) {
        System.out.print("Текст из файла: ");
        // чтение файла
        try(FileReader fr = new FileReader(fileName)){
            // читаем посимвольно
            int c;
            while((c = fr.read()) != -1){
                System.out.print((char)c);
            }
        }catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}

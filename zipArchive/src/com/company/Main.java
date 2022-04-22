package com.company;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        zipFile();
        unzipFile();
        Delete();
    }

    private static void Delete() {
        File file = new File("file.txt");
        File archive = new File("C:/Users/nikit/JavaProjects/zipArchive/arсhive.zip");
        System.out.print("\nУдалить файл и архив? Y/N: ");
        Scanner scanner = new Scanner(System.in);;
        String choice = scanner.nextLine();
        if (Objects.equals(choice, "Y") || Objects.equals(choice, "y")){
            if (file.delete() && archive.delete()) {
                System.out.println(file.getName() + " and " + archive.getName() + " deleted");
            } else {
                System.out.println("not deleted");
            }
        }
    }

    private static void unzipFile() throws IOException {
        String archive = "C:/Users/nikit/JavaProjects/zipArchive/arсhive.zip";
        File destDir = new File("C:/Users/nikit/JavaProjects/zipArchive/unzip_file");

        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(archive));
        ZipEntry zipEntry = zipInputStream.getNextEntry();

        byte[] buffer = new byte[zipInputStream.available()];

        while (zipEntry != null) {
            File newFile = new File(destDir, String.valueOf(zipEntry));

            File parent = newFile.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                throw new IOException("Failed to create directory " + parent);
            }

            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            int len;
            while ((len = zipInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();

            zipEntry = zipInputStream.getNextEntry();
        }

        zipInputStream.closeEntry();
        zipInputStream.close();
        System.out.print("Файл разархивирован: ");

        try(FileReader fr = new FileReader("file.txt")){
            int c;
            while((c = fr.read()) != -1){
                System.out.print((char)c);
            }
        }catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }


    private static void zipFile() throws IOException {

        FileOutputStream fileOutputStream = new FileOutputStream("arсhive.zip");
        ZipOutputStream zip = new ZipOutputStream(fileOutputStream);

        File fileToZip = new File("file.txt");
        FileInputStream fileInputStream = new FileInputStream(fileToZip);

        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zip.putNextEntry(zipEntry);

        byte[] buffer = new byte[fileInputStream.available()];
        int length;
        while((length = fileInputStream.read(buffer)) >= 0) {
            zip.write(buffer, 0, length);
        }

        System.out.println("Файл "+zipEntry+" добавлен в архив");

        zip.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}

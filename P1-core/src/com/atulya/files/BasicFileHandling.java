package com.atulya.files;

import java.io.*;
import java.util.Scanner;

public class BasicFileHandling {
    public static void main(String[] args) {
//        File file = createFile("src/com/atulya/files/papi.txt");
        long writelen = writeToFile(
                "src/com/atulya/files/papi.txt",
                true,
                "Chacha Chutadi Lal",
                "Manus Chaubey"
        );

        System.out.println("Written: " + writelen);

        String[] lines = {"Lorem ipsum", "Dolor"};
        writelen = writeToFile(
                "src/com/atulya/files/papi.txt",
                true,
                lines
        );

        System.out.println("Written: " + writelen);

        System.out.println(readFile("src/com/atulya/files/papi.txt"));

    }

    static File createFile(String filepath) {
        try {
            File file = new File(filepath);
            if (file.exists()) {
                long fileLen = file.length();
                System.out.println("File exists and has length: " + fileLen);
            }

            if (!file.exists()) {
                boolean success = file.createNewFile();
                System.out.println("File didn't exist. Was it created: " + success);
            }

            String path = file.getAbsolutePath();
            System.out.println(path);

            return file;
        } catch (IOException e) {
            e.printStackTrace();

            // if we don't throw a new exception then we will have to
            // provide another return outside the try-catch block
            throw new IllegalArgumentException(e);
        }
    }

    static String readFile(String path) {
        File file = new File(path);
        StringBuilder content = new StringBuilder();
        try (
                Scanner fileReader = new Scanner(file)
        ) {
            while (fileReader.hasNext()) {
                content.append(fileReader.nextLine()).append('\n');
            }

            return content.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException(e);
        }
    }

    static long writeToFile(String path, boolean append, String... lines) {
        File file = createFile(path);
        long initialLength = file.length();
        try (
                FileWriter fw = new FileWriter(file, append);
                PrintWriter printWriter = new PrintWriter(fw);
        ) {


            for (String line : lines) {
                printWriter.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }

        // file contents are updated only after fileWriter is closed
        return file.length() - initialLength;
    }
}

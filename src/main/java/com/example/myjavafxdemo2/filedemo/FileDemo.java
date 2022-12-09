package com.example.myjavafxdemo2.filedemo;

import java.io.*;

public class FileDemo {
    public static void main(String[] args)  {
        String filePath = "C:\\Users\\dongl\\Desktop\\test.txt";
        // 写入文件
        File file = new File(filePath);
        System.out.println(file.exists());
//        for (int i = 0; i < 10; i++) {
//            appendToFile(file,
//                    "❤💛💚💙💜🤎🧡🐖🐖\n"
//            );
//        }
//        // 读取文件
//        ReadFromFile(filePath);
    }

    public static void readFromFile(String filePath) {
        // 打开文件读取文件并打印
        try(
                FileReader fileReader = new FileReader(filePath); // FileReader是字符流，读取的是字符
        ) {
            int ch;
            while ((ch = fileReader.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 写入文件
    public static void writeToFile(String filePath, String content)  {
        // 写入文件
        try (
                FileWriter fileWriter = new FileWriter(filePath);
        ) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 在文件末尾追加内容
    public static void appendToFile(File file, String content)  {
        // 加入文件
        try (
                FileWriter fileWriter = new FileWriter(file, true);
        ) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

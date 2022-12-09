package com.example.myjavafxdemo2.filedemo;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProcessString {
    public static void main(String[] args) {
        String str =    "1 (mousePress) 1 (btn) 717 481 (pos)\n" +
                        "2 (mouseRelease) 1 (btn) 717 481 (pos)\n" +
                        "3 (mouseWheelMove) -1 (deg)\n" +
                        "4 (keyPress) 57 (空格)\n" +
                        "5 (keyRelease) 57 (空格)\n";
        // region file test
        String filePath = "C:\\Users\\dongl\\Desktop\\test.txt";
//        File file = new File(filePath);
//        writeToFile(filePath, str);
//        readFromFile(filePath);
        readFromFileAndProcess(filePath);
        // endregion

//        int[] buffer; // 用于存储读取到的字符
//        // 将换行符替换为空格 以便于分割
//        str = str.replace("\r", " ");
//        str = str.replace("\n", " ");
//        buffer = java.util.Arrays.stream(str.split(" "))
//                .filter(s -> s.matches("\\d+")) // 过滤出数字 \\d+表示一个或多个数字
//                .mapToInt(Integer::parseInt)
//                .toArray();
//        for (int i : buffer) {
//            System.out.println(i);
//        }
    }
    // 从字符串中提取出数字
    public static int[] getNumsFromString(String input) {
        // 使用streamAPI将字符串数组转换为整数数组
        return java.util.Arrays.stream(input.split(" "))
                .filter(s -> s.matches("-?\\d+")) // 过滤出数字 \\d+表示一个或多个数字 如果需要读入负数则需要修改正则表达式为 -?\\d+
                .mapToInt(Integer::parseInt)
                .toArray();
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

    // 读取文件并提取数字处理
    public static void readFromFileAndProcess(String filePath) {
        // 打开文件读取文件并打印
        try(
                FileReader fileReader = new FileReader(filePath); // FileReader是字符流，读取的是字符
        ) {
            int ch;
            StringBuilder stringBuilder = new StringBuilder();
            while ((ch = fileReader.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            int[] nums = getNumsFromString(stringBuilder.toString()
                            .replace("\r", " ")
                            .replace("\n", " ")
            );
            for (int i : nums) {
                System.out.println(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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
}

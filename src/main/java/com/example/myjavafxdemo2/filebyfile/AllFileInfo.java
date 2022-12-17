package com.example.myjavafxdemo2.filebyfile;

import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class AllFileInfo {

    // 待处理文件的队列
    private static final Vector<FileRecord> fileRecords = new Vector<FileRecord>();

    // 添加文件
    public static void pushFile(FileRecord fileRecord) {
        fileRecords.add(fileRecord);
    }
    // 删除文件
    public static void popFile() {
        fileRecords.remove(fileRecords.size() - 1);
    }
    // 获取文件
    public static FileRecord getFile(int fileIndex) {
        return fileRecords.get(fileIndex);
    }
    // 获取文件数量
    public static int getFileCount() {
        return fileRecords.size();
    }
    // 获取文件名
    public static String getFileName(int fileIndex) {
        return fileRecords.get(fileIndex).getFileNameProperty().get();
    }
    // 获取loopTimes
    public static int getLoopTimes(int fileIndex) {
        return fileRecords.get(fileIndex).getLoopTimes();
    }

    // 获取stepDelay
    public static int getStepDelay(int fileIndex) {
        return fileRecords.get(fileIndex).getStepDelay();
    }

    // 获取loopDelay
    public static int getLoopDelay(int fileIndex) {
        return fileRecords.get(fileIndex).getLoopDelay();
    }

    // 获取文件路径
    public static String getFilePath(int fileIndex) {
        return fileRecords.get(fileIndex).getFilePath();
    }

}

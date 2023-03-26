package com.performance.service;

import com.performance.utils.ResponseMsg;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * @program: performance-test-core
 * @description: Test I/O performance
 * @author: yfliu
 * @create: 2023-03-25 17:09
 **/
@Service
public class FileService {

    private static final String FILE_PATH = "file.txt";


    public ResponseMsg fileReadTest(long fileSize) {
        createFile(fileSize);
        return ResponseMsg.ok("Time taken to read file: " + getFileReadTime(FILE_PATH) + " ms");
    }

    public ResponseMsg fileWriteTest(long fileSize) {
        return ResponseMsg.ok("Time taken to write file: " + createFile(fileSize) + " ms");
    }

    private long getFileReadTime(String filePath) {
        int bufferSize = 8192; // 8 KB
        long startTime = System.currentTimeMillis(); // get current time(ns)
        try (BufferedReader br = new BufferedReader(new FileReader(filePath), bufferSize)) {
            String line;
            while ((line = br.readLine()) != null) {
                // read
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private long createFile(long fileSizeMB) {
        String filePath = FILE_PATH;
        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
        long timeUsed = writeNewFile(fileSizeMB, filePath);
        return timeUsed;
    }

    private long writeNewFile(long fileSizeMB, String filePath) {
        long fileSize = fileSizeMB * 1024 * 1024; // 文件大小（1 MB）

        byte[] data = new byte[1024]; // 缓冲区大小（1 KB）
        Random random = new Random();
        random.nextBytes(data); // 将缓冲区填充为随机数据

        File file = new File(filePath);
        long startTime = System.currentTimeMillis(); // 获取当前时间（纳秒）
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            long remainingBytes = fileSize;
            while (remainingBytes > 0) {
                int bytesToWrite = (int) Math.min(remainingBytes, data.length);
                bos.write(data, 0, bytesToWrite);
                remainingBytes -= bytesToWrite;
            }
            System.out.println("File created and saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}

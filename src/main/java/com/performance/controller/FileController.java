package com.performance.controller;

import com.performance.service.FileService;
import com.performance.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: performance-test-core
 * @description: File I/O Test Part
 * @author: yfliu
 * @create: 2023-03-25 17:11
 **/

@RestController()
@RequestMapping("/FileTest")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/fileReadTest")
    public ResponseMsg fileReadTest(@RequestParam long fileSize) {
        return fileService.fileReadTest(fileSize);
    }

    @PostMapping(value = "/fileWriteTest")
    public ResponseMsg fileWriteTest(@RequestParam long fileSize) {
        return fileService.fileWriteTest(fileSize);
    }
}

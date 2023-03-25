package com.performance.controller;

import com.performance.service.MatrixMultiplicationService;
import com.performance.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: performance-test-core
 * @description: Matrix multiplication
 * @author: yfliu
 * @create: 2023-03-25 15:34
 **/

@RestController()
@RequestMapping("/MatrixMultiplicationTest")
public class MatrixMultiplicationController {

    @Autowired
    private MatrixMultiplicationService matrixMultiplicationService;

    @PostMapping(value = "/randomMatrixMultiplicationTest")
    public ResponseMsg matrixMultiplicationTest(@RequestParam() Integer size) {
        return matrixMultiplicationService.testMatrixMultiplicationPerformance(size);
    }


    @PostMapping(value = "/averageMatrixMultiplicationTest")
    public ResponseMsg averageMatrixMultiplicationTest(@RequestParam Integer generations, @RequestParam() Integer size) {
        return matrixMultiplicationService.averageMatrixMultiplicationPerformance(generations,size);
    }

}

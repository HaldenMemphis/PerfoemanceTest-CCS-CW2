package com.performance.service;

import com.performance.utils.ResponseMsg;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Random;

/**
 * @program: performance-test-core
 * @description: service
 * @author: yfliu
 * @create: 2023-03-25 15:43
 **/
@Service
public class MatrixMultiplicationService {

    public ResponseMsg testMatrixMultiplicationPerformance(Integer size) {
        return ResponseMsg.ok(performanceTest(size) + "ms");
    }

    public ResponseMsg averageMatrixMultiplicationPerformance(Integer generation,Integer size) {
        ArrayList<Long> timeList = new ArrayList<>();
        for(int i=0;i<generation;i++){
            performanceTest(size);
            timeList.add(performanceTest(size));
        }
        double average = timeList.stream().mapToLong(Long::longValue).average().orElse(0.0);
        return ResponseMsg.ok(average + "ms");
    }


    private long performanceTest(Integer size) {
        double[][] a = generateMatrix(size, size);
        double[][] b = generateMatrix(size, size);
        long start1 = System.currentTimeMillis();
        multiplyMatricesViaCommonsMath(a, b);
        long end1 = System.currentTimeMillis();
        return end1 - start1;
    }

    //via Apache Commons Math
    private double[][] multiplyMatricesViaCommonsMath(double[][] matrix1, double[][] matrix2) {
        return multiply(matrix1, matrix2);
    }

    // Wrapping matrix multiplication as a private function
    private static double[][] multiply(double[][] a, double[][] b) {
        RealMatrix matrixA = new Array2DRowRealMatrix(a);
        RealMatrix matrixB = new Array2DRowRealMatrix(b);
        RealMatrix matrixC = matrixA.multiply(matrixB);
        return matrixC.getData();
    }

    // Randomly generate a matrix of size rows x cols
    public static double[][] generateMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble() * 100;  // 随机生成 0-100 之间的 double 类型数据
            }
        }
        return matrix;
    }

}

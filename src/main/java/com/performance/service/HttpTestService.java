package com.performance.service;

import com.performance.utils.ResponseMsg;
import org.springframework.stereotype.Service;

/**
 * @program: performance-test-core
 * @description: handle the process
 * @author: yfliu
 * @create: 2023-03-24 16:43
 **/
@Service
public class HttpTestService {

    public ResponseMsg httpTest() {
        return ResponseMsg.error(1, "test");
    }

}

package com.performance.controller;

import com.performance.service.HttpTestService;
import com.performance.utils.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: performance-test-core
 * @description: This part designed to receive the HTTP request from the client and test the HTTP process Performance
 * @author: yfliu
 * @create: 2023-03-24 11:12
 **/

@RestController()
@RequestMapping("/HTTPTest")
public class HttpTestController {

    @Autowired
    private HttpTestService httpTestService;

    //create a request controller to recevice http request
    @PostMapping(value = "/processHTTPRequest")
    public ResponseMsg process() {
        return httpTestService.httpTest();
    }



}

package com.hirehub.user.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.common.utils.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ApiResponse<Map<String,String>> home(){

        Map<String,String> data = Map.of(

                "application","HireHub API",
                "version","1.0.0",
                "status","Running"

        );

        return ResponseUtil.success(data,"API is working");

    }

}
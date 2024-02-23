package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class DemoController {
//    @GetMapping("/")
//    public String rootPage(){
//        return "abc";
//    }
    @GetMapping("/page")
    public String homePage(){
         return "index";
    }

}

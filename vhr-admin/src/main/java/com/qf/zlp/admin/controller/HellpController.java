package com.qf.zlp.admin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*@CrossOrigin(origins ="http://10.3.142.21:8080/")*/
public class HellpController {


    @GetMapping("/employee/basic/hello")
    public String hello() {
        return "/employee/basic/hello";
    }
    @GetMapping("/employee/advanced/hello")
    public String hello2() {
        return "/employee/advanced/hello";
    }
}

package com.qf.zlp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.qf.zlp.framework.mapper","com.qf.zlp.system.mapper"})
public class VhrApp {

    public static void main(String[] args) {

        SpringApplication.run(VhrApp.class,args);
    }
}

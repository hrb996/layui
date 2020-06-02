package com.hrb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hrb.mapper")
public class LayuixmApplication {

    public static void main(String[] args) {

        SpringApplication.run(LayuixmApplication.class, args);

    }

}

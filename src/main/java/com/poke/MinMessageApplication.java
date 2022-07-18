package com.poke;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.poke.Mapper")
public class MinMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinMessageApplication.class, args);
    }

}

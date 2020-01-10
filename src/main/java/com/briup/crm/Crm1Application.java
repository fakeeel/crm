package com.briup.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.briup.crm.dao")
public class Crm1Application {
	public static void main(String[] args) {
		SpringApplication.run(Crm1Application.class, args);
	}

}

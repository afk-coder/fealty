package com.fux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 程序入口
 */
@SpringBootApplication
@ServletComponentScan(basePackages = {"com.fux.auth.druid.*"})
public class FealtyAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(FealtyAdminApplication.class, args);
	}

}

package com.example.ocr.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebocrApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebocrApplication.class, args);
		//System.out.println("os is : "+System.getProperty("os.name"));
	}

}

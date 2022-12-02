package com.goodee.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class ThorAuctionApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ThorAuctionApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ThorAuctionApplication.class, args);//
	}

}

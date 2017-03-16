package com.picanco.menuboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class MenuBoardApplication {

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/", "MenuBoard", "password");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}

	public static void main(String[] args) {
		SpringApplication.run(MenuBoardApplication.class, args);
	}

}

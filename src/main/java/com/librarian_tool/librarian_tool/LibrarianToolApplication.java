package com.librarian_tool.librarian_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;


@SpringBootApplication()
public class LibrarianToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarianToolApplication.class, args);
	}

}

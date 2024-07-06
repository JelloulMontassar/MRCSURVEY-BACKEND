package com.crm.organizecrm;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ci")

class OrganizeCrmApplicationTests {

	@Value("${host}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;
	@PostConstruct
	void init() {
		System.out.println("DATABASE URL: " + url);
		System.out.println("DATABASE USERNAME: " + username);
		System.out.println("DATABASE PASSWORD: " + password);
	}
	@Test
	void contextLoads() {
		System.out.println("DATABASE URL: " + url);
		System.out.println("DATABASE USERNAME: " + username);
		System.out.println("DATABASE PASSWORD: " + password);
	}
}

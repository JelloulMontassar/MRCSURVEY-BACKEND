package com.crm.organizecrm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrganizeCrmApplicationTests {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Test
	void contextLoads() {
		System.out.println("DATABASE URL: " + url);
		System.out.println("DATABASE USERNAME: " + username);
		System.out.println("DATABASE PASSWORD: " + password);
	}
}

package com.crm.organizecrm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrganizeCrmApplicationTests {
	@Value("${spring.datasource.url}")
	private String url;

	@Test
	void contextLoads() {
		System.out.println("DATABASE URL");
	}


}

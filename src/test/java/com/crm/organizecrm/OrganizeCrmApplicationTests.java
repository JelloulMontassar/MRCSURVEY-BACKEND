package com.crm.organizecrm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ci")
@AutoConfigureTestDatabase
class OrganizeCrmApplicationTests {


	@Test
	void contextLoads() {

	}
}

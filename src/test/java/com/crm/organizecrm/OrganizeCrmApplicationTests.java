package com.crm.organizecrm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("ci")
@PropertySource("classpath:bootstrap-ci.yml")
//if test are running locally then use the below annotation
//@ActiveProfiles("test")
//@PropertySource("classpath:bootstrap-test.yml")
class OrganizeCrmApplicationTests {

	@Test
	void contextLoads() {

	}
}

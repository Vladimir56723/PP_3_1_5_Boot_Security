package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kata.spring.boot_security.demo.model.User;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {

	@Test
	void contextLoads() {
		User user=new User();
		System.out.println(user.toString());
	}

}

package com.example.desafiospring;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.okta.desafiospring.DesafiospringApplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesafiospringApplicationTests {

	@Test
	void contextLoads() {
		DesafiospringApplication desafio = new DesafiospringApplication();
		assertNull(desafio.getClass());
	}

}

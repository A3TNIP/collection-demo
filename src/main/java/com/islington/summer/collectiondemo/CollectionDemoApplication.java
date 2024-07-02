package com.islington.summer.collectiondemo;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CollectionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectionDemoApplication.class, args);
	}

	@PostConstruct
	public void init() {
		log.info("CollectionDemoApplication init");
	}

}

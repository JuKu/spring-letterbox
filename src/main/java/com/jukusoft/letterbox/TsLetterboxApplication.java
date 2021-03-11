package com.jukusoft.letterbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.jukusoft.letterbox", "com.jukusoft.authentification.jwt"})
@EnableCaching
@EnableScheduling
@EntityScan({"com.jukusoft.letterbox", "com.jukusoft.authentification.jwt"})
@EnableJpaRepositories({"com.jukusoft.letterbox", "com.jukusoft.authentification.jwt"})
public class TsLetterboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsLetterboxApplication.class, args);
	}

}

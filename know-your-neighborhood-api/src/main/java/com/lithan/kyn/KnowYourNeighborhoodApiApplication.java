package com.lithan.kyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.lithan.kyn.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EntityScan("com.lithan.kyn.entity")
public class KnowYourNeighborhoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KnowYourNeighborhoodApiApplication.class, args);
	}

}

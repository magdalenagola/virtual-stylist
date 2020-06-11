package com.codecool.virtualstylist;

import com.codecool.virtualstylist.configuration.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class VirtualStylistApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualStylistApplication.class, args);
	}

}

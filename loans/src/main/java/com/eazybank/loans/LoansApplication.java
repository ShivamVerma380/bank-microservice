package com.eazybank.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.eazybank.loans.dto.LoansContactInfoDto;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@OpenAPIDefinition(
	info = @Info(
		title = "Loans microservice REST API documentation",
		description = "Eazybank Loans microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "Shivam Verma",
			email = "shivamvermasv380@gmail.com",
			url = "https://github.com/ShivamVerma380"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://github.com/ShivamVerma380"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "EazyBank Loans microservice REST API Documentation",
		url = "http://localhost:8090/swagger-ui/index.html"
	)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}

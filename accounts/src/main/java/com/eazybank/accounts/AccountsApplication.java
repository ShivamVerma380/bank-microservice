package com.eazybank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title = "Accounts microservice REST API documentation",
		description = "Eazybank Account microservice REST API Documentation",
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
		description = "EazyBank Account microservice REST API Documentation",
		url = "http://localhost:8080/swagger-ui/index.html"
	)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);}

}

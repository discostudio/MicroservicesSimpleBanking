package com.fhc.AccountsService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
			title = "this is the title of Accounts API REST documentation.",
			description = "this is the description of the documentation of the API",
			version = "v1",
			contact = @Contact(
				name = "person name of the responsible for the API",
				email = "person@email",
				url = "www.ddd.com"
			),
			license = @License(
				name = "license name",
				url = "www.urldetais.com"
			)
		), externalDocs = @ExternalDocumentation(
				description = "external docs description",
				url = "dummyurl.com"
			)
)
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}

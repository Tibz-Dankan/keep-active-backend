package com.example.keepactivebackend;

import com.example.keepactivebackend.auth.AuthenticationService;
import com.example.keepactivebackend.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.keepactivebackend.user.Role.ADMIN;
import static com.example.keepactivebackend.user.Role.MANAGER;

@SpringBootApplication
public class KeepActiveBackend {

	public static void main(String[] args) {
		SpringApplication.run(KeepActiveBackend.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.username("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.username("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}

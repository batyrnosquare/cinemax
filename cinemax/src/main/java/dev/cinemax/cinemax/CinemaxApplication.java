package dev.cinemax.cinemax;

import dev.cinemax.cinemax.entity.Role;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CinemaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaxApplication.class, args);
		System.out.println(String.valueOf(Role.ADMIN));
	}

	@GetMapping("/")
	public String apiIndex(){
		return "CINEMAX";
	}


}

package dev.cinemax.cinemax;

import dev.cinemax.cinemax.entity.Role;
import dev.cinemax.cinemax.entity.User;
import dev.cinemax.cinemax.repo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
@RestController
public class CinemaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaxApplication.class, args);
	}

	@GetMapping("/")
	public String apiIndex(){
		return "CINEMAX";
	}


}

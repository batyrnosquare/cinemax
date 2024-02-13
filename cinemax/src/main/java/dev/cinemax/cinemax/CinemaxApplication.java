package dev.cinemax.cinemax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"it.uniroma3.siw"})
public class SiwSpringOfficinaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiwSpringOfficinaApplication.class, args);
	}

}

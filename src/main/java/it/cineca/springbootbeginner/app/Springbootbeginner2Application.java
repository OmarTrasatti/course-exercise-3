package it.cineca.springbootbeginner.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import it.cineca.springbootbeginner.models.Box;
import it.cineca.springbootbeginner.repository.BoxRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"it.cineca.springbootbeginner.repository"})
@EntityScan(basePackages = {"it.cineca.springbootbeginner"})
@ComponentScan(basePackages = {"it.cineca.springbootbeginner"})
public class Springbootbeginner2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springbootbeginner2Application.class, args);
	}
	
    @Bean
    public CommandLineRunner init(BoxRepository boxRepo) {
        return (args) -> {
            boxRepo.save(new Box(null, "HeartBox", "MyAuthor", null));
            boxRepo.save(new Box(null, "RoundedBox", "AnotherAuthor", null));
            boxRepo.save(new Box(null, "SquaredBox", "YetAnotherAuthor", null));
            System.out.println("Creazione box completata");             
        };
    }
}

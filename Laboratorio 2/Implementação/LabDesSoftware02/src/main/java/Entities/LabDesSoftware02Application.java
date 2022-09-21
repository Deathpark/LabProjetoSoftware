package Entities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages = {"Entities"})
@EnableJpaRepositories(basePackages = {"Repository"})
@ComponentScan(basePackages = {"Controllers"})
//@RestController
public class LabDesSoftware02Application {

	public static void main(String[] args) {
		SpringApplication.run(LabDesSoftware02Application.class, args);
	}

	//@GetMapping("/hello")
	//public String hello(@RequestParam(value = "name", defaultValue = "World") String name, @RequestParam(value = "othername", defaultValue = "World²") String othername) {
	//	return String.format("Hello %s %s!", name, othername);
	//}
}

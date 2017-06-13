package projApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@SpringBootApplication
@Configuration
public class SpringBootMainApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootMainApp.class, args);
	}

}
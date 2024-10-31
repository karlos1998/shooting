package it.letscode.shooting;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.letscode.shooting.Question.Question;
import it.letscode.shooting.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ShootingApplication implements CommandLineRunner {

	@Autowired
	private QuestionRepository questionRepository;

    @Autowired
	@Qualifier("webApplicationContext")
	private ResourceLoader resourceLoader;

	public static void main(String[] args) {
		SpringApplication.run(ShootingApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		for (String arg : args) {
			if ("--init-db".equals(arg)) {
				questionRepository.deleteAll();
				Resource resource = resourceLoader.getResource("classpath:questions.json");
				ObjectMapper objectMapper = new ObjectMapper();
				List<Question> questions = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Question>>() {});

				questionRepository.saveAll(questions);
				System.out.println("Zapisano " + questions.size() + " rekordów do bazy danych.");
				System.exit(0);
			}
		}
	}
}

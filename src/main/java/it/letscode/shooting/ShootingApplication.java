package it.letscode.shooting;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.letscode.shooting.Question.Question;
import it.letscode.shooting.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ShootingApplication implements CommandLineRunner {

	@Autowired
	private QuestionRepository questionRepository;

	public static void main(String[] args) {
		SpringApplication.run(ShootingApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		for (String arg : args) {
			if ("--init-db".equals(arg)) {
				questionRepository.deleteAll();
				File answersFile = new File("src/main/resources/questions.json");
				ObjectMapper objectMapper = new ObjectMapper();
				List<Question> questions = objectMapper.readValue(answersFile, new TypeReference<List<Question>>() {});
				questionRepository.saveAll(questions);
				System.out.println("Zapisano " + questions.size() + " rekordów do bazy danych.");
				System.exit(0);
			}
		}
	}
}

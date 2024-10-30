package it.letscode.shooting.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getRandomQuestion() {
        long count = questionRepository.count();
        if (count == 0) {
            return Optional.empty();
        }

        Random random = new Random();
        int randomIndex = random.nextInt((int) count);

        return questionRepository.findAll().stream().skip(randomIndex).findFirst();
    }
}

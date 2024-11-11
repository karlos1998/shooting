package it.letscode.shooting.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Optional<Question> getRandomQuestion(Set<String> solvedQuestions) {
        List<Question> availableQuestions = questionRepository.findAll().stream()
                .filter(question -> !solvedQuestions.contains(question.getId()))
                .collect(Collectors.toList());

        if (availableQuestions.isEmpty()) {
            return Optional.empty();
        }

        Random random = new Random();
        int randomIndex = random.nextInt(availableQuestions.size());

        return Optional.of(availableQuestions.get(randomIndex));
    }

    public int getUnresolvedQuestionsCount(Set<String> solvedQuestions) {
        List<Question> availableQuestions = questionRepository.findAll().stream()
                .filter(question -> !solvedQuestions.contains(question.getId()))
                .toList();

        return availableQuestions.size();
    }
}

package it.letscode.shooting.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/random")
    public String getRandomQuestion(Model model) {
        Optional<Question> optionalQuestion = questionService.getRandomQuestion();
        if (optionalQuestion.isEmpty()) {
            model.addAttribute("message", "Brak pytań w bazie danych.");
            return "random-question";
        }

        Question question = optionalQuestion.get();
        model.addAttribute("question", question);
        return "random-question";
    }
}

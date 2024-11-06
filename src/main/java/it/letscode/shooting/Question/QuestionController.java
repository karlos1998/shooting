package it.letscode.shooting.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/random")
    public String getRandomQuestion(Model model, HttpSession session) {
        Set<String> solvedQuestions = (Set<String>) session.getAttribute("solvedQuestions");
        if (solvedQuestions == null || solvedQuestions.size() >= 279) { //TODO - straszny drut z lenistwa, jak ten caly program :)
            solvedQuestions = new HashSet<>();
            session.setAttribute("solvedQuestions", solvedQuestions);
        }

        Optional<Question> optionalQuestion = questionService.getRandomQuestion(solvedQuestions);
        if (optionalQuestion.isEmpty()) {
            model.addAttribute("message", "Brak nowych pytań w bazie danych.");
            return "random-question";
        }

        Question question = optionalQuestion.get();
        model.addAttribute("question", question);

        model.addAttribute("solvedQuestionsCount", (long) solvedQuestions.size());

        return "random-question";
    }

    // Nowy endpoint do oznaczania pytania jako rozwiązane
    @PostMapping("/mark-solved")
    public String markQuestionAsSolved(@RequestParam("questionId") String questionId, HttpSession session) {
        Set<String> solvedQuestions = (Set<String>) session.getAttribute("solvedQuestions");
        if (solvedQuestions == null) {
            solvedQuestions = new HashSet<>();
        }

        solvedQuestions.add(questionId);
        session.setAttribute("solvedQuestions", solvedQuestions);
        return "redirect:/questions/random";
    }
}

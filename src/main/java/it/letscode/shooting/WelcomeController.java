package it.letscode.shooting;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String welcome(Model model, HttpSession session) {

        Set<String> solvedQuestions = (Set<String>) session.getAttribute("solvedQuestions");

        int solvedQuestionsCount = solvedQuestions != null ? solvedQuestions.size() : 0;
        model.addAttribute("solvedQuestionsCount", solvedQuestionsCount);

        return "welcome";
    }
}

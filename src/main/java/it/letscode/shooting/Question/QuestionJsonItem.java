package it.letscode.shooting.Question;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.EnumMap;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class QuestionJsonItem {

    private String title;

    private EnumMap<Question.AnswerKey, String> answers;

    @JsonProperty("correct_answer")
    private Question.AnswerKey correctAnswer;
}

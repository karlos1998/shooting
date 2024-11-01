package it.letscode.shooting.Question;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.EnumMap;

@Document(collection = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("answers")
    private EnumMap<AnswerKey, String> answers;

    private AnswerKey correctAnswer;

    public enum AnswerKey {
        A, B, C
    }
}

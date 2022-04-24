package ro.fasttrackit.exament.chestionar.questions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddQuestionModel {

    private String question;
    private String option1;
    private boolean option1IsCorrect;
    private String option2;
    private boolean option2IsCorrect;
    private String option3;
    private boolean option3IsCorrect;
    private String imageUrl;
}

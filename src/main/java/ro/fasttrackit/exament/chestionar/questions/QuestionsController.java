package ro.fasttrackit.exament.chestionar.questions;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.exament.chestionar.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuestionsController {

    private final QuestionService questionService;

    @GetMapping(path = "")
    public List<QuestionEntity> findAll() {
        return questionService.findAll();
    }

    @GetMapping(path = "/{questionId}")
    public QuestionEntity getQuestionById(@PathVariable Long questionId) {
        return questionService.getQuestionById(questionId);
    }

    @PatchMapping(path = "/{questionId}")
    public void updateQuestion(@RequestBody AddQuestionModel payload, @PathVariable Long questionId) {
        questionService.updateQuestion(questionId, payload);
    }

    @GetMapping(path = "/random")
    public QuestionEntity getRandomQuestion() {
        return questionService.getRandomQuestion();
    }

    @PostMapping(path = "")
    public void addQuestion(@RequestBody AddQuestionModel payload) {
        questionService.addQuestion(payload);
    }
}

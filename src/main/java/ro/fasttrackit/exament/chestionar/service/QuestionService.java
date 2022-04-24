package ro.fasttrackit.exament.chestionar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.fasttrackit.exament.chestionar.questionoptions.QuestionOptionsEntity;
import ro.fasttrackit.exament.chestionar.questionoptions.QuestionOptionsRepository;
import ro.fasttrackit.exament.chestionar.questions.AddQuestionModel;
import ro.fasttrackit.exament.chestionar.questions.QuestionEntity;
import ro.fasttrackit.exament.chestionar.questions.QuestionRepository;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuestionService {
    public static final Random RANDOM = new Random();

    private final QuestionRepository repository;
    private final QuestionOptionsRepository questionOptionsRepository;

    public List<QuestionEntity> findAll() {
        return repository.findAll();
    }
    public QuestionEntity getQuestionById(Long questionId) {
        return repository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + questionId));
    }

    public void updateQuestion(Long questionId, AddQuestionModel payload) {
        QuestionEntity questionEntity = getQuestionById(questionId);

        questionEntity.setQuestion(payload.getQuestion());
        questionEntity.setImageUrl(payload.getImageUrl());
        List<QuestionOptionsEntity> optionsEntities = questionEntity.getQuestionOptions();
        for (int i=0; i< optionsEntities.size(); i++) {
            if (i == 0) {
                optionsEntities.get(i).setOption(payload.getOption1());
                optionsEntities.get(i).setCorrect(payload.isOption1IsCorrect());
            } else if(i==1) {
                optionsEntities.get(i).setOption(payload.getOption2());
                optionsEntities.get(i).setCorrect(payload.isOption2IsCorrect());
            } else {
                optionsEntities.get(i).setOption(payload.getOption3());
                optionsEntities.get(i).setCorrect(payload.isOption3IsCorrect());
            }
        }
        questionOptionsRepository.saveAll(optionsEntities);
        repository.save(questionEntity);
    }

    public QuestionEntity getRandomQuestion() {
        List<Long> allIds = repository.findAllIds();
        int questionIndex = RANDOM.nextInt(allIds.size());

        return repository.findById(allIds.get(questionIndex))
                .orElseThrow(() -> new RuntimeException("No questions available"));
    }

    public void addQuestion(AddQuestionModel payload) {
        QuestionEntity questionEntity = mapPayloadToQuestionEntity(payload);
        repository.save(questionEntity);
    }

    private QuestionEntity mapPayloadToQuestionEntity(AddQuestionModel payload) {
        QuestionOptionsEntity questionOptions1 = QuestionOptionsEntity.builder()
                .option(payload.getOption1())
                .correct(payload.isOption1IsCorrect())
                .build();
        QuestionOptionsEntity questionOptions2 = QuestionOptionsEntity.builder()
                .option(payload.getOption2())
                .correct(payload.isOption2IsCorrect())
                .build();
        QuestionOptionsEntity questionOptions3 = QuestionOptionsEntity.builder()
                .option(payload.getOption3())
                .correct(payload.isOption3IsCorrect())
                .build();

        List<QuestionOptionsEntity> questionOptionsEntities = questionOptionsRepository.saveAll(List.of(
                questionOptions1, questionOptions2, questionOptions3
        ));

        return QuestionEntity.builder()
                .question(payload.getQuestion())
                .questionOptions(questionOptionsEntities)
                .imageUrl(payload.getImageUrl())
                .build();
    }
}

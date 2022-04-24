package ro.fasttrackit.exament.chestionar.questions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(value = "SELECT q.id FROM QuestionEntity q")
    List<Long> findAllIds();
}

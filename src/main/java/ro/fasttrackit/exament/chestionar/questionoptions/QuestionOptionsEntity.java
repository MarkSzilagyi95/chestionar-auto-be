package ro.fasttrackit.exament.chestionar.questionoptions;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_options")
public class QuestionOptionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_options_id_seq")
    @SequenceGenerator(name = "question_options_id_seq", sequenceName = "question_options_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "option")
    private String option;

    @Column(name = "is_correct")
    private boolean correct;

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}

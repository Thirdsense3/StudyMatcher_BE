package studyMatcherSpring.studyMatcherSpring.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudyCategory {

    @Id @GeneratedValue
    @Column(name = "study_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;
}

package studyMatcherSpring.studyMatcherSpring.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StudyPage {

    @Id @GeneratedValue
    @Column(name = "study_page_id")
    private Long id;

    private String path;
}

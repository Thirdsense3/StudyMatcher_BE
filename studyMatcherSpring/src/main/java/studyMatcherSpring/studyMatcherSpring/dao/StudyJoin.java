package studyMatcherSpring.studyMatcherSpring.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class StudyJoin {

    @Id @GeneratedValue
    @Column(name = "study_join_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public static StudyJoin createStudyJoin(Study study, Member member) {
        StudyJoin studyJoin = new StudyJoin();
        studyJoin.setStudy(study);
        studyJoin.setMember(member);

        study.getStudyJoins().add(studyJoin);
        member.getMyStudies().add(studyJoin);

        return studyJoin;
    }
}

package studyMatcherSpring.studyMatcherSpring.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Study {

    @Id @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    private String name;

    private String text;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<StudyJoin> studyJoins = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private List<StudyCategory> categories = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "study_page_id")
    private StudyPage studyPage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Member leader;


    //--연관관계 편의 메서드--//
    public void addStudyJoin(StudyJoin studyJoin) {
        studyJoins.add(studyJoin);
        studyJoin.setStudy(this);
    }

    public void leaderMember(Member member) {
        leader = member;
        location = member.getLocation();
        member.getLeadingStudy().add(this);
    }

    //--생성 메서드--//
    public void introPath(String path) {
        text = path;
    }

    protected Study() {
    }

    private Study(StudyBuilder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.level = builder.level;
        this.status = Status.RECRUIT;
        for(StudyCategory category : builder.categories) {
            this.getCategories().add(category);
        }
    }

    public static class StudyBuilder {

        private String name;
        private Level level = Level.BRONZE;
        private Type type = Type.UNTACT;
        private List<StudyCategory> categories = new ArrayList<>();

        public StudyBuilder(String name) {
            this.name = name;
        }
        public Study build() {
            return new Study(this);
        }

        public StudyBuilder setLevel(Level level) {
            if(level == null)
                return this;
            this.level = level;
            return this;
        }

        public StudyBuilder setType(Type type) {
            if(type == null)
                return this;
            this.type = type;
            return this;
        }

        public StudyBuilder setCategories(List<StudyCategory> categories) {
            for(StudyCategory category : categories) {
                this.categories.add(category);
            }

            return this;
        }
    }
}

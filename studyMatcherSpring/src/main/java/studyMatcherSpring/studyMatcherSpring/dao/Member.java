package studyMatcherSpring.studyMatcherSpring.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String nickname;

    private String password;

    @Embedded
    private Location location;

    @Enumerated(EnumType.STRING)
    private Level level;

    private LocalDate testDate = null;

    @OneToMany(mappedBy = "member")
    private List<TimeTable> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<StudyJoin> myStudies = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatRoomJoin> myChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ChatMessage> messages = new ArrayList<>();

    @OneToMany(mappedBy = "leader")
    private List<Study> leadingStudy = new ArrayList<>();

    //--연관관계 편의 메서드--//
    public void addStudyJoin(StudyJoin studyJoin) {
        this.getMyStudies().add(studyJoin);
        studyJoin.setMember(this);
    }
}

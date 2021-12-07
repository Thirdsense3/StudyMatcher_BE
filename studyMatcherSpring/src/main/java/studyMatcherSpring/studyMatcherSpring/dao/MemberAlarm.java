package studyMatcherSpring.studyMatcherSpring.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class MemberAlarm {
    @Id
    @GeneratedValue
    @Column(name = "alarm_id")
    private Long alarm_id;
    private Long member_id;

    private LocalTime timestamp;

    private String notification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

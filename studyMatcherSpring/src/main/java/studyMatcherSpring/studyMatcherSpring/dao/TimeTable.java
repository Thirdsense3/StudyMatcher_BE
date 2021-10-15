package studyMatcherSpring.studyMatcherSpring.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class TimeTable {

    @Id @GeneratedValue
    @Column(name = "time_table_id")
    private Long id;

    private LocalDate date;

    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

package studyMatcherSpring.studyMatcherSpring.dao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {

    @Id @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    private String message;

    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
}

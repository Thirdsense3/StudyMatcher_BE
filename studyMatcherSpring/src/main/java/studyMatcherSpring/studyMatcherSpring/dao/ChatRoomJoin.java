package studyMatcherSpring.studyMatcherSpring.dao;

import javax.persistence.*;

@Entity
public class ChatRoomJoin {

    @Id @GeneratedValue
    @Column(name = "chat_room_join_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;
}

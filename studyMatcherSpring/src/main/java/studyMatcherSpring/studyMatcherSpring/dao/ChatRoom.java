package studyMatcherSpring.studyMatcherSpring.dao;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChatRoom {

    @Id @GeneratedValue
    @Column(name = "chat_room_id")
    private Long id;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatRoomJoin> chatting_members;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> messages;
}

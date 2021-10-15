package studyMatcherSpring.studyMatcherSpring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import studyMatcherSpring.studyMatcherSpring.dao.*;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {

            Member member = createMember("UserA", Level.BRONZE, "userA", new Location("seoul", "gangnam"), "1234");
            em.persist(member);

            Study study = createStudy(member, "test1", Type.UNTACT, Level.BRONZE, "hello Spring");
            em.persist(study);

            StudyJoin.createStudyJoin(study, member);
        }

        public void dbInit2() {

            Member member = createMember("UserB", Level.PLATINUM, "userB", new Location("incheon", "yeonsu"), "1234");
            em.persist(member);

            Study study = createStudy(member, "test1", Type.CONTACT, Level.SILVER, "hello JPA");
            em.persist(study);

            Study study2 = createStudy(member, "test3", Type.CONTACT, Level.SILVER, "hello Spring2");
            em.persist(study2);

            StudyJoin.createStudyJoin(study, member);
            StudyJoin.createStudyJoin(study2, member);
        }

        private Study createStudy(Member member, String test2, Type contact, Level silver, String s) {
            Study study = new Study.StudyBuilder(test2).setType(contact).setLevel(silver).build();
            study.introPath(s);
            study.leaderMember(member);
            return study;
        }

        private Member createMember(String name, Level level, String nickname, Location location, String pw) {
            Member member = new Member();
            member.setName(name);
            member.setLevel(level);
            member.setNickname(nickname);
            member.setLocation(location);
            member.setPassword(pw);
            return member;
        }
    }
}

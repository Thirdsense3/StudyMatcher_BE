package studyMatcherSpring.studyMatcherSpring.repository;

import studyMatcherSpring.studyMatcherSpring.dao.Member;
import studyMatcherSpring.studyMatcherSpring.dao.MemberAlarm;

import javax.persistence.EntityManager;
import java.util.List;

public interface MemberRepository {

    Member save(Member member);
    List<Member> findByNickname(String nickname);
    List<Member> findAll();
    Member findById(Long id);
    List<MemberAlarm> LoadAlarm(Long id);
}

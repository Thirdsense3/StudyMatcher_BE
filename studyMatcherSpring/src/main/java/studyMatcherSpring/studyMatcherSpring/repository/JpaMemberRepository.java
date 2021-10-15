package studyMatcherSpring.studyMatcherSpring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import studyMatcherSpring.studyMatcherSpring.dao.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaMemberRepository implements MemberRepository{

    @Autowired
    private EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findByNickname(String nickname) {
        return em.createQuery("select m from Member m where nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }
}

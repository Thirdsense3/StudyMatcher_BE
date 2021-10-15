package studyMatcherSpring.studyMatcherSpring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studyMatcherSpring.studyMatcherSpring.dao.Level;
import studyMatcherSpring.studyMatcherSpring.dao.Member;
import studyMatcherSpring.studyMatcherSpring.repository.MemberRepository;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MemberServiceV1 implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Member save(Member member) {
        member.setLevel(Level.UNRANKED);
        return memberRepository.save(member);
    }

    public Long login(Member member) {

        List<Member> members = memberRepository.findByNickname(member.getNickname());
        if(members.isEmpty()) {
            return 0L;
            //throw new IllegalStateException("wrong email");
        }
        Member findMember = members.get(0);

        log.debug("nickname={}, password={}", findMember.getNickname(), findMember.getPassword());
        log.debug("nickname={}, password={}", member.getNickname(), member.getPassword());
        if(!member.getPassword().equals(findMember.getPassword())) {
            return 0L;
            //throw new IllegalStateException("wrong password");
        }
        return findMember.getId();
    }

    public Boolean validateDuplicatedNickname(String nickname) {

        List<Member> members = memberRepository.findByNickname(nickname);
        if(members.isEmpty()) {
            return true;
            //throw new IllegalStateException("wrong id");
        }
        return false;
    }

    public Member getMemberInform(Long id) {
        return memberRepository.findById(id);
    }
}

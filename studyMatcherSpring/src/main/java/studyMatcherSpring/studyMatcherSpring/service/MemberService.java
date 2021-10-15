package studyMatcherSpring.studyMatcherSpring.service;

import studyMatcherSpring.studyMatcherSpring.dao.Member;

public interface MemberService {

    Member save(Member member);
    Long login(Member member);
    Boolean validateDuplicatedNickname(String nickname);
    Member getMemberInform(Long id);
}

package studyMatcherSpring.studyMatcherSpring.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import studyMatcherSpring.studyMatcherSpring.dao.Level;
import studyMatcherSpring.studyMatcherSpring.dao.Location;
import studyMatcherSpring.studyMatcherSpring.dao.Member;
import studyMatcherSpring.studyMatcherSpring.service.MemberServiceV1;

@SpringBootTest
@Transactional
class MemberServiceV1Test {

    @Autowired
    MemberServiceV1 memberService;

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setNickname("wooky9633");
        member.setName("kong");
        member.setPassword("1234");
        Location location = new Location("인천", "연수");
        member.setLocation(location);
        member.setLevel(Level.PLATINUM);

        // when
        Member saveMember = memberService.save(member);

        // then
        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    public void duplicateCheck() {
        // given
        Member member = new Member();
        member.setNickname("wooky9633");
        member.setName("kong");
        member.setPassword("1234");
        Location location = new Location("인천", "연수");
        member.setLocation(location);
        member.setLevel(Level.PLATINUM);
        memberService.save(member);

        // when
        Boolean found = memberService.validateDuplicatedNickname(member.getNickname());
        Boolean notFound = memberService.validateDuplicatedNickname("");

        // then
        assertThat(found).isEqualTo(false);
        assertThat(notFound).isEqualTo(true);
    }

    @Test
    public void login() {
        // given
        Member member = new Member();
        member.setNickname("wooky9633");
        member.setName("kong");
        member.setPassword("1234");
        Location location = new Location("인천", "연수");
        member.setLocation(location);
        member.setLevel(Level.PLATINUM);
        memberService.save(member);

        Member loginMember = new Member();
        loginMember.setNickname("wooky9633");
        loginMember.setPassword("1234");

        Member wrongEmail = new Member();
        wrongEmail.setNickname("wooky9633@naver.com");
        wrongEmail.setPassword("1234");

        Member wrongPassword = new Member();
        wrongPassword.setNickname("wooky9633");
        wrongPassword.setPassword("1233");

        // when
        Long resultId = memberService.login(loginMember);
        Long wrong1 = memberService.login(wrongEmail);
        Long wrong2 = memberService.login(wrongPassword);

        // then
        assertThat(resultId).isEqualTo(member.getId());
        assertThat(wrong1).isEqualTo(0L);
        assertThat(wrong2).isEqualTo(0L);
    }

    @Test
    public void getMemberInform() {
        //given
        Member member = new Member();
        member.setNickname("wooky9633");
        member.setName("kong");
        member.setPassword("1234");
        Location location = new Location("인천", "연수");
        member.setLocation(location);
        member.setLevel(Level.PLATINUM);
        memberService.save(member);

        //when
        Member memberInform = memberService.getMemberInform(member.getId());

        //then
        assertThat(member).isEqualTo(memberInform);
    }
}

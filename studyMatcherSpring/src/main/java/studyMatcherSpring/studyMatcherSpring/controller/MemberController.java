package studyMatcherSpring.studyMatcherSpring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import studyMatcherSpring.studyMatcherSpring.dao.Member;
import studyMatcherSpring.studyMatcherSpring.dto.ResponseMember;
import studyMatcherSpring.studyMatcherSpring.service.MemberService;
import studyMatcherSpring.studyMatcherSpring.service.MemberServiceV1;

// 임시 조치
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Member join(@RequestBody  Member member) {
        return memberService.save(member);
    }

    @PostMapping("/login")
    public ResponseMember login(@RequestBody Member member) {

        Long loginId = memberService.login(member);

        if(loginId == 0L) {
            return new ResponseMember("wrong id or password");
        }
        return new ResponseMember(loginId);
    }

    @GetMapping("/duplicate-check/{nickname}")
    public String duplicateCheck(@PathVariable String nickname) {
        if(memberService.validateDuplicatedNickname(nickname)) {
            return "possible id";
        }
        return "duplicated id";
    }

    @GetMapping("/{id}")
    public Member getMemberInform(@PathVariable Long id) {
        Member member = memberService.getMemberInform(id);

        Member resultMember = new Member();
        resultMember.setNickname(member.getNickname());
        resultMember.setName(member.getName());
        resultMember.setId(member.getId());
        resultMember.setLevel(member.getLevel());
        resultMember.setTestDate(member.getTestDate());
        resultMember.setLocation(member.getLocation());

        return resultMember;
    }
}

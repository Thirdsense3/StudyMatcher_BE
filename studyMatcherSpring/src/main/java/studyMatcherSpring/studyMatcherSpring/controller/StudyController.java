package studyMatcherSpring.studyMatcherSpring.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import studyMatcherSpring.studyMatcherSpring.dao.*;
import studyMatcherSpring.studyMatcherSpring.dto.ResponseData;
import studyMatcherSpring.studyMatcherSpring.repository.StudySearch;
import studyMatcherSpring.studyMatcherSpring.service.StudyService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin("http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/study")
public class StudyController {

    private final StudyService studyService;
    //private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<CreateStudyResponse> enroll(@RequestBody @Valid CreateStudyRequest request, BindingResult result) {

        if(result.hasErrors()) {
            // 에러 메시지 추가
            return ResponseEntity.badRequest().build();
        }

        // 카테고리 추가
        Study study = new Study.StudyBuilder(request.getName())
                .setType(request.getType())
                .setLevel(request.getLevel())
                .build();

        Long studyId = studyService.enroll(study, request.getText(), request.getLeader_id());
        Study enroll = studyService.getStudyInform(studyId);

        CreateStudyResponse response = new CreateStudyResponse();
        response.setId(enroll.getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseStudyInform getStudyInform(@PathVariable("id") Long study_id) {
        Study study = studyService.getStudyInform(study_id);
        ResponseStudyInform response = new ResponseStudyInform();

        response.setName(study.getName());

        MemberDto memberDto = new MemberDto();
        Member leader = study.getLeader();
        memberDto.setMember_id(leader.getId());
        memberDto.setMember_level(leader.getLevel());
        memberDto.setNickname(leader.getNickname());
        response.setLeader(memberDto);

        response.setType(study.getType());
        response.setStatus(study.getStatus());
        response.setLevel(study.getLevel());
        response.setLocation(study.getLocation());

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(study.getText()));
            String text = new String(bytes);
            response.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @GetMapping
    public Result findStudies(String name, String leader, Long... category) {
        StudySearch studySearch = new StudySearch();
        studySearch.setStudyName(name);
        studySearch.setLeader(leader);

        // 카테고리로 검색 기능 추가
        /*for(Long id : category) {
            studySearch.getCategories().add(id);
        }*/

        List<Study> allStudy = studyService.findAllStudy(studySearch);
        ArrayList<StudyDto> result = new ArrayList<>();

        for(Study study : allStudy) {
            StudyDto studyDto = new StudyDto();

            studyDto.setId(study.getId());
            studyDto.setLeader(study.getLeader().getNickname());
            studyDto.setStatus(study.getStatus());
            studyDto.setLevel(study.getLevel());
            studyDto.setLocation(study.getLocation());
            studyDto.setType(study.getType());
            studyDto.setName(study.getName());

            result.add(studyDto);
        }

        return new Result(result.size(), result);
    }

    @PostMapping("/join")
    public StudyDto joinStudy(@RequestBody JoinRequest joinRequest) {

        Study study = studyService.joinStudy(joinRequest.getMember_id(), joinRequest.getStudy_id());
        StudyDto studyDto = new StudyDto();

        studyDto.setId(study.getId());
        studyDto.setLeader(study.getLeader().getNickname());
        studyDto.setStatus(study.getStatus());
        studyDto.setLevel(study.getLevel());
        studyDto.setLocation(study.getLocation());
        studyDto.setType(study.getType());
        studyDto.setName(study.getName());
        return studyDto;
    }

    /**
     * TODO(exception 추가 )
     * */
    @PostMapping("/{id}/status")
    public Boolean changeStatus(@PathVariable("id") Long studyId){

        return studyService.changeStatus(studyId);
    }

    @Data
    static class JoinRequest {
        private Long member_id;
        private Long study_id;
    }

    @Data
    static class CreateStudyRequest {
        @NotEmpty
        private String name;

        @NotEmpty
        private String text;

        @Positive @NotNull
        private Long leader_id;

        private Level level;
        private Type type;
        private ArrayList<Long> category = new ArrayList<>();
    }

    @Data
    static class CreateStudyResponse {
        private Long id;
    }

    @Data
    static class ResponseStudyInform {
        private String name;
        private String text;
        private Level level;
        private MemberDto leader;
        private Type type;
        private Status status;
        private Location location;
    }

    @Data
    static class MemberDto {
        private Long member_id;
        private String nickname;
        private Level member_level;
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {

        private int count;
        private T data;
    }

    @Data
    static class StudyDto {
        private Long id;
        private String name;
        private Level level;
        private String leader;
        private Type type;
        private Status status;
        private Location location;
    }
}

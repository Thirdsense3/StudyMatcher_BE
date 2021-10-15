package studyMatcherSpring.studyMatcherSpring.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studyMatcherSpring.studyMatcherSpring.dao.Member;
import studyMatcherSpring.studyMatcherSpring.dao.Study;
import studyMatcherSpring.studyMatcherSpring.dao.StudyJoin;
import studyMatcherSpring.studyMatcherSpring.repository.MemberRepository;
import studyMatcherSpring.studyMatcherSpring.repository.StudyRepository;
import studyMatcherSpring.studyMatcherSpring.repository.StudySearch;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyServiceV1 implements StudyService{

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long enroll(Study study, String text, Long leader_id) {

        Member member = memberRepository.findById(leader_id);
        study.leaderMember(member);

        StudyJoin.createStudyJoin(study, member);

        studyRepository.save(study);
        createIntroFile(study, text);

        return study.getId();
    }

    @Override
    public List<Study> findAllStudy(StudySearch studySearch) {
        return studyRepository.findAll(studySearch);
    }

    @Override
    public Study getStudyInform(Long id) {
        return studyRepository.findOne(id);
    }

    private void createIntroFile(Study study, String text) {
        String path = "C:\\StudyMatcherFiles\\" + study.getId();
        String filename = path + "\\intro.txt";

        try {
            File folder = new File(path);
            if(!folder.exists())
                folder.mkdir();

            File file = new File(filename);

            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.flush();
            fw.close();

            study.introPath(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package studyMatcherSpring.studyMatcherSpring.service;

import studyMatcherSpring.studyMatcherSpring.dao.Study;
import studyMatcherSpring.studyMatcherSpring.repository.StudySearch;

import java.util.List;

public interface StudyService {

    Long enroll(Study study, String text, Long leader_id);
    List<Study> findAllStudy(StudySearch studySearch);
    Study getStudyInform(Long id);
}

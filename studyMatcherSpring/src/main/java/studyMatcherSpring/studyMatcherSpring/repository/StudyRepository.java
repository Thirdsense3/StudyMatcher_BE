package studyMatcherSpring.studyMatcherSpring.repository;

import studyMatcherSpring.studyMatcherSpring.dao.Study;
import studyMatcherSpring.studyMatcherSpring.dao.StudyJoin;

import java.util.List;

public interface StudyRepository {

    Study findById(Long id);
    List<Study> findAll(StudySearch studySearch);
    void save(Study study);
    Boolean changeStatus(Long id, String studyStatus);
}

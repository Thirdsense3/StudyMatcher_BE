package studyMatcherSpring.studyMatcherSpring.repository;

import studyMatcherSpring.studyMatcherSpring.dao.Study;

import java.util.List;

public interface StudyRepository {

    Study findOne(Long id);
    List<Study> findAll(StudySearch studySearch);
    void save(Study study);
}

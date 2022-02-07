package studyMatcherSpring.studyMatcherSpring.repository;

import studyMatcherSpring.studyMatcherSpring.dao.Alarm;

import java.util.List;

public interface AlarmRepository {

    void save(Alarm alarm);
    List<Alarm> load(Long id);
}

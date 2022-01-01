package studyMatcherSpring.studyMatcherSpring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import studyMatcherSpring.studyMatcherSpring.dao.Alarm;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlarmRepositoryV1 implements AlarmRepository{

    private final EntityManager em;

    @Override
    public void save(Alarm alarm) {
        em.persist(alarm);
    }

    @Override
    public List<Alarm> load(Long id) {
        return em.createQuery("select ma from Alarm as ma where member_id = :id", Alarm.class)
                .setParameter("id", id)
                .getResultList();
    }
}

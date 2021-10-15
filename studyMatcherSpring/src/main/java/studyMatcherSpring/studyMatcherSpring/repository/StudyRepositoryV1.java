package studyMatcherSpring.studyMatcherSpring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import studyMatcherSpring.studyMatcherSpring.dao.Study;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudyRepositoryV1 implements StudyRepository{

    private final EntityManager em;

    @Override
    public Study findOne(Long id) {
        return em.find(Study.class, id);
    }

    @Override
    public List<Study> findAll(StudySearch studySearch) {
        // QueryDSL로 수정
        // 정규표현식으로 변경
        String jpql = "select s From Study s join s.leader m";
        boolean isFirstCondition = true;

        if(StringUtils.hasText(studySearch.getLeader())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.nickname like :nickname";
        }

        if(StringUtils.hasText(studySearch.getStudyName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " s.name like :name";
        }

        /*if(studySearch.getCategories().size() != 0) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
        }*/

        TypedQuery<Study> query = em.createQuery(jpql, Study.class)
                .setMaxResults(1000);
        if(StringUtils.hasText(studySearch.getLeader())) {
            query = query.setParameter("nickname", studySearch.getLeader());
        }
        if(StringUtils.hasText(studySearch.getStudyName())) {
            query = query.setParameter("name", studySearch.getStudyName());
        }
        /*if(studySearch.getCategories().size() != 0) {

        }*/
        return query.getResultList();
    }

    @Override
    public void save(Study study) {
        em.persist(study);
    }
}

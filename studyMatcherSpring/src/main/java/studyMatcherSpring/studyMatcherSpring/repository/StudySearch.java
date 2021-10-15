package studyMatcherSpring.studyMatcherSpring.repository;

import lombok.Getter;
import lombok.Setter;
import studyMatcherSpring.studyMatcherSpring.dao.Category;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class StudySearch {

    private String leader;
    private List<Long> categories = new ArrayList<>();
    private String studyName;

    public void addCategories(Long id) {
        this.getCategories().add(id);
    }
}

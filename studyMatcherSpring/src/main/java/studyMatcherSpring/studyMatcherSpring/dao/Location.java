package studyMatcherSpring.studyMatcherSpring.dao;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Location {

    private String city;
    private String gu;

    public Location() {
    }

    public Location(String city, String gu) {
        this.city = city;
        this.gu = gu;
    }
}

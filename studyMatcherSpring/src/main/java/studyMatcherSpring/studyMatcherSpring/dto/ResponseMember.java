package studyMatcherSpring.studyMatcherSpring.dto;

import lombok.Getter;

@Getter
public class ResponseMember {

    private Long id;
    private String error;

    public ResponseMember(Long id) {
        this.id = id;
    }

    public ResponseMember(String error) {
        this.error = error;
    }
}

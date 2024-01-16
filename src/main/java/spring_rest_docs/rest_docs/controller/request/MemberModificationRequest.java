package spring_rest_docs.rest_docs.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberModificationRequest {

    private String name;

    public MemberModificationRequest(String name) {
        this.name = name;
    }
}

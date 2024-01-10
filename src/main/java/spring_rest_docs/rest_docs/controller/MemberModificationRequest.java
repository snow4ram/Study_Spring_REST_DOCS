package spring_rest_docs.rest_docs.controller;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberModificationRequest {

    private String name;

    public MemberModificationRequest(String name) {
        this.name = name;
    }
}

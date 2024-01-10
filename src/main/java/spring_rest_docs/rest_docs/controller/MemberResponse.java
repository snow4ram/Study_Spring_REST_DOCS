package spring_rest_docs.rest_docs.controller;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import spring_rest_docs.rest_docs.dto.MemberDTO;

import java.time.LocalDateTime;

@Getter
public class MemberResponse {

    private final Long id;
    private String email;
    private final String name;
    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public MemberResponse(MemberDTO memberDTO) {
        this.id = memberDTO.id();
        this.email = memberDTO.email();
        this.name = memberDTO.name();
        this.createdAt = memberDTO.createdAt();
        this.updatedAt = memberDTO.updatedAt();
    }
}

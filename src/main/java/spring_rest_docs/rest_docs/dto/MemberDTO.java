package spring_rest_docs.rest_docs.dto;

import java.time.LocalDateTime;

public record MemberDTO (
        Long id,
        String email,
        String name,
        LocalDateTime createdAt,

        LocalDateTime updatedAt
){
}

package spring_rest_docs.rest_docs.dto;

import org.springframework.stereotype.Service;
import spring_rest_docs.rest_docs.entity.Member;

import java.util.function.Function;


@Service
public class MemberDTOMapper implements Function<Member, MemberDTO> {
    @Override
    public MemberDTO apply(Member memberEntity) {
        return new MemberDTO(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getName(),
                memberEntity.getCreatedAt(),
                memberEntity.getUpdatedAt()
        );
    }
}

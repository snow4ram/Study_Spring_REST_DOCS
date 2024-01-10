package spring_rest_docs.rest_docs.dto;

import org.springframework.stereotype.Service;
import spring_rest_docs.rest_docs.entity.MemberEntity;

import java.util.function.Function;


@Service
public class MemberDTOMapper implements Function<MemberEntity , MemberDTO> {
    @Override
    public MemberDTO apply(MemberEntity memberEntity) {
        return new MemberDTO(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getName(),
                memberEntity.getCreatedAt(),
                memberEntity.getUpdatedAt()
        );
    }
}

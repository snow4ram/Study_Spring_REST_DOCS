package spring_rest_docs.rest_docs.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring_rest_docs.rest_docs.controller.request.MemberModificationRequest;
import spring_rest_docs.rest_docs.controller.request.MemberSignupRequest;
import spring_rest_docs.rest_docs.dto.MemberDTO;
import spring_rest_docs.rest_docs.dto.MemberDTOMapper;
import spring_rest_docs.rest_docs.entity.Member;
import spring_rest_docs.rest_docs.repository.JpaMemberRepository;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final JpaMemberRepository repository;

    private final MemberDTOMapper mapper;



    @Transactional
    public MemberDTO save(MemberSignupRequest memberInformationRequest) {

        if (Objects.isNull(memberInformationRequest)){
            throw new RuntimeException("사용자 정보가 없습니다.");
        }

        Member Member = new Member(memberInformationRequest.getEmail(), memberInformationRequest.getName());

        Member members = repository.save(Member);

        return mapper.apply(members);

    }


    @Transactional(readOnly = true)
    public MemberDTO findMember(Long memberId) {
        Member Member = repository.findById(memberId).orElseThrow(
                () -> new RuntimeException("사용자에 대한 정보가 없습니다."));

        return mapper.apply(Member);
    }


    @Transactional
    public MemberDTO modifyMemberDetailsPut(Long memberId, MemberModificationRequest memberModificationRequest) {
        Member findMember = repository.findById(memberId).orElseThrow(() -> new RuntimeException("사용자의 대한 정보가 없습니다."));


        findMember.modification(memberModificationRequest);

        log.info("찾은 사용자 정보 = {} ", findMember);

        return mapper.apply(findMember);

    }

    @Transactional
    public MemberDTO modifyMemberDetailsPatch(Long memberId, MemberModificationRequest memberModificationRequest) {
        Member findMember = repository.findById(memberId).orElseThrow(() -> new RuntimeException("사용자의 대한 정보가 없습니다."));


        findMember.modification(memberModificationRequest);

        log.info("찾은 사용자 정보 = {} ", findMember);

        return mapper.apply(findMember);

    }
}

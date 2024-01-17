package spring_rest_docs.rest_docs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring_rest_docs.rest_docs.controller.request.MemberModificationRequest;
import spring_rest_docs.rest_docs.controller.request.MemberSignupRequest;
import spring_rest_docs.rest_docs.controller.response.MemberResponse;
import spring_rest_docs.rest_docs.dto.MemberDTO;
import spring_rest_docs.rest_docs.service.MemberService;

import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService members;

    private final MessageSource messageSource;

    @GetMapping("/find/{memberId}")
    public ResponseEntity<MemberResponse> findMemberInformation(@PathVariable(name = "memberId") Long memberId) {
        MemberDTO member = members.findMember(memberId);

        MemberResponse memberResponse = new MemberResponse(member);

        return ResponseEntity.ok().body(memberResponse);
    }

    @GetMapping("/hateoas/{memberId}")
    public EntityModel<MemberResponse> findMemberInformationHateoas(@PathVariable(name = "memberId") Long memberId) {
        MemberDTO member = members.findMember(memberId);

        MemberResponse memberResponse = new MemberResponse(member);

        EntityModel<MemberResponse> entityModel = EntityModel.of(memberResponse);

        WebMvcLinkBuilder linTO = linkTo(methodOn(this.getClass()).retrieveAllUser());

        entityModel.add(linTO.withRel("all-users"));

        return entityModel;
    }

    @GetMapping("/member-all")
    public List<MemberResponse> retrieveAllUser() {

        return members.memberFindAll().stream().map(MemberResponse::new).toList();
    }



    @PostMapping("/create")
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Validated MemberSignupRequest memberSignupRequest) {

        log.info("요청 정보 확인 = {}" , memberSignupRequest.getClass());

        MemberDTO result = members.save(memberSignupRequest);

        MemberResponse memberCreationCompleted = new MemberResponse(result);

        return ResponseEntity.ok().body(memberCreationCompleted);
    }


    @PutMapping("/modify/{memberId}")
    public ResponseEntity<MemberResponse> update
            (@PathVariable(name = "memberId") Long memberId,
             @RequestBody MemberModificationRequest memberModificationRequest) {

        MemberDTO update = members.modifyMemberDetailsPut(memberId, memberModificationRequest);


        MemberResponse response = new MemberResponse(update);

        return ResponseEntity.ok().body(response);


    }

    @PatchMapping("/modify/{memberId}")
    public ResponseEntity<MemberResponse> modifyPatch(
            @PathVariable(name = "memberId") Long memberId,
            @RequestBody MemberModificationRequest memberModificationRequest) {


        MemberDTO update = members.modifyMemberDetailsPatch(memberId, memberModificationRequest);


        MemberResponse response = new MemberResponse(update);

        return ResponseEntity.ok().body(response);
    }


}

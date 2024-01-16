package spring_rest_docs.rest_docs.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring_rest_docs.rest_docs.controller.request.MemberModificationRequest;
import spring_rest_docs.rest_docs.controller.request.MemberSignupRequest;
import spring_rest_docs.rest_docs.controller.response.MemberResponse;
import spring_rest_docs.rest_docs.dto.MemberDTO;
import spring_rest_docs.rest_docs.service.MemberService;

@Slf4j
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService members;

    @GetMapping("/find/{memberId}")
    public ResponseEntity<MemberResponse> findMemberInformation(@PathVariable(name = "memberId") Long memberId) {
        MemberDTO member = members.findMember(memberId);

        MemberResponse memberResponse = new MemberResponse(member);

        return ResponseEntity.ok().body(memberResponse);
    }


    @PostMapping("/create")
    public ResponseEntity<MemberResponse> createMember(@RequestBody @Validated MemberSignupRequest memberSignupRequest) {

        MemberDTO save = members.save(memberSignupRequest);

        MemberResponse memberCreationCompleted = new MemberResponse(save);

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

package spring_rest_docs.rest_docs.controller;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSignupRequest {

    @Email
    private String email;

    @NotEmpty
    private String name;
}

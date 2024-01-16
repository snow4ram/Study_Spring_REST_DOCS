package spring_rest_docs.rest_docs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import spring_rest_docs.rest_docs.controller.request.MemberModificationRequest;
import spring_rest_docs.rest_docs.dto.MemberDTO;
import spring_rest_docs.rest_docs.service.MemberService;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
@ExtendWith(SpringExtension.class)
class MemberPatchApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService service;


    @Test
    public void modify_member_patch()throws Exception {

        given(service.modifyMemberDetailsPatch(eq(1L) , any(MemberModificationRequest.class)))
                .willReturn(new MemberDTO(
                        1L , "xxx7949@naver.com" ,"중성" ,
                        LocalDateTime.of(2024,01,12, 00,21,40),
                        LocalDateTime.of(2024,01,12 ,00,21,40)
                ));

        MemberModificationRequest memberSignupRequest = new MemberModificationRequest("중성");


        mvc.perform(patch("/api/members/modify/{memberId}" , 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(memberSignupRequest)))
                .andDo(document(
                        "members/modifyPATCHAPIMemberName",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(jsonPath("$.email").value("xxx7949@naver.com"))
                .andExpect(jsonPath("$.name").value("중성"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
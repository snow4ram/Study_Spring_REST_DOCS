package spring_rest_docs.rest_docs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import spring_rest_docs.rest_docs.controller.request.MemberSignupRequest;
import spring_rest_docs.rest_docs.dto.MemberDTO;
import spring_rest_docs.rest_docs.service.MemberService;

import java.time.LocalDateTime;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
@ExtendWith(SpringExtension.class)
public class MemberPostApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService service;


    @Test
    public void member_create() throws Exception {

        given(service.save(any(MemberSignupRequest.class)))
                .willReturn(new MemberDTO(
                        1L , "dfo444@naver.com" ,"명사면" ,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ));

        MemberSignupRequest memberSignupRequest = new MemberSignupRequest("dfo444@naver.com", "명사면");

        mvc.perform(post("/api/members/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(memberSignupRequest)))
                .andExpect(jsonPath("$.email").value("dfo444@naver.com"))
                .andExpect(jsonPath("$.name").value("명사면"))
                .andDo(document(
                        "members/POSTAPIMemberCreate",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(STRING).description("이메일"),
                                        fieldWithPath("name").type(STRING).description("이름")
                                )
                        )))
                .andExpect(status().isOk())
                .andDo(print());

    }
}

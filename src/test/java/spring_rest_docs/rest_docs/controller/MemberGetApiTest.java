package spring_rest_docs.rest_docs.controller;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import spring_rest_docs.rest_docs.dto.MemberDTO;
import spring_rest_docs.rest_docs.service.MemberService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@AutoConfigureRestDocs
@WebMvcTest(MemberController.class)
@ExtendWith(SpringExtension.class)
public class MemberGetApiTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService service;


    @Test
    @DisplayName("GET PathVariable 통해 사용자 정보 찾기 ")
    public void find_member() throws Exception {

        given(service.findMember(1L)).willReturn(new MemberDTO(
                1L , "dfo444@naver.com" ,"명사면" ,
                LocalDateTime.of(2023,03,9 ,17,46,33),
                LocalDateTime.of( 2024,01,10 ,20,03,58)
        ));

        mvc.perform(get("/api/members/find/{memberId}" ,1L))
                .andExpect(jsonPath("$.email").value("dfo444@naver.com"))
                .andExpect(jsonPath("$.name").value("명사면"))
                .andDo(document(
                        "members/GETAPIFindMemberById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                        ))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void find() throws Exception {

        mvc.perform(get("/api/members/find/{memberId}" ,1L))
                .andDo(print())
                .andDo(document(
                        "members/GETAPIFindMemberById",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .andExpect(status().isOk())
                .andDo(print());
    }

}

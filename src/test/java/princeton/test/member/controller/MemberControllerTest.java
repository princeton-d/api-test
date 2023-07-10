package princeton.test.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.member.domain.dto.request.RequestMemberLoginDto;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.service.MemberService;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        RequestMemberSignupDto initDto = RequestMemberSignupDto.builder()
                .username("username")
                .password("aA1!password")
                .build();

        memberService.signup(initDto);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void signupTest() throws Exception {
        //given
        RequestMemberSignupDto signupDto = RequestMemberSignupDto.builder()
                .username("helloName")
                .password("aA1!password")
                .build();

        //when, then
        mockMvc.perform(post("/api/members/signup")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupDto))

                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    @DisplayName("잘못된 username 회원가입 실패 테스트")
    void signupFailTest() throws Exception {
        //given
        RequestMemberSignupDto signupDto = RequestMemberSignupDto.builder()
                .username("varyVaryVaryLongUsername")
                .password("aA1!password")
                .build();

        //when, then
        mockMvc.perform(post("/api/members/signup")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupDto))
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("data").exists())
                .andExpect(jsonPath("data.status").exists())
                .andExpect(jsonPath("data.error").exists())
                .andExpect(jsonPath("data.message").exists());
    }

    @Test
    @DisplayName("로그인 테스트")
    void loginTest() throws Exception {
        //given
        RequestMemberLoginDto requestDto = RequestMemberLoginDto.builder()
                .username("username")
                .password("aA1!password")
                .build();

        //when, then
        mockMvc.perform(post("/api/members/login")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists("Authorization"));
    }

}
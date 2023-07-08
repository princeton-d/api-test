package princeton.test.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.service.MemberService;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static princeton.test.util.mapper.MemberMapper.*;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    @Test
    @WithMockUser
    @DisplayName("회원가입 테스트")
    void signupTest() throws Exception {
        //given
        RequestMemberSignupDto signupDto = RequestMemberSignupDto.builder()
                .username("username")
                .password("aA1!password")
                .build();

        //when, then
        mockMvc.perform(post("/api/members/signup")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(signupDto))
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    @WithMockUser
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
                        .with(csrf())
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("data").exists())
                .andExpect(jsonPath("data.status").exists())
                .andExpect(jsonPath("data.error").exists())
                .andExpect(jsonPath("data.message").exists());
    }

}
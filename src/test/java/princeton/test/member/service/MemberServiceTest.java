package princeton.test.member.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        RequestMemberSignupDto signupDto = RequestMemberSignupDto.builder()
                .username("username")
                .password("aA1!password")
                .build();

        memberService.signup(signupDto);
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void memberJoinTest() {
        //given
        RequestMemberSignupDto signupDto = RequestMemberSignupDto.builder()
                .username("princeton")
                .password("aA1!password")
                .build();

        //when
        memberService.signup(signupDto);

        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("회원가입 username 중복으로 실패 테스트")
    void validateDuplicateUsernameTest() {
        //given
        RequestMemberSignupDto duplicateUsernameDto = RequestMemberSignupDto.builder()
                .username("username")
                .password("aA1!password")
                .build();

        //when, then
        assertThatThrownBy(() -> memberService.signup(duplicateUsernameDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 존재하는 username입니다. username은 중복될 수 없습니다.");
    }

}
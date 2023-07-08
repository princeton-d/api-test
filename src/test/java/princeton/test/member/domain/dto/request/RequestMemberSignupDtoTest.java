package princeton.test.member.domain.dto.request;


import jakarta.validation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class RequestMemberSignupDtoTest {

    private Validator validator;
    private String username;
    private String password;

    @BeforeEach

    void beforeEach() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();

        username = "username";
        password = "Aa!1password";
    }


    @Test
    @DisplayName("회원가입 요청 dto 검증 성공 테스트")
    void requestMemberSignupDtoSuccess() {
        //given
        RequestMemberSignupDto signupDto = createSignupDto(username, password);

        //when
        Set<ConstraintViolation<RequestMemberSignupDto>> result = validator.validate(signupDto);

        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("4자 미만 username 가입 실패 테스트")
    void shortUsernameSignupTest() {
        //given
        String shortUsername = "a";
        RequestMemberSignupDto shortUsernameDto = createSignupDto(shortUsername, password);

        //when
        ConstraintViolation<RequestMemberSignupDto> shortUsernameData = validator.validate(shortUsernameDto).iterator().next();

        //then
        assertThat(shortUsernameData.getInvalidValue()).isEqualTo(shortUsername);
    }

    @Test
    @DisplayName("10자 초과 username 가입 실패 테스트")
    void longUsernameSignupTest() {
        //given
        String longUsername = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        RequestMemberSignupDto longUsernameDto = createSignupDto(longUsername, password);

        //when
        ConstraintViolation<RequestMemberSignupDto> longUsernameData = validator.validate(longUsernameDto).iterator().next();

        //then
        assertThat(longUsernameData.getInvalidValue()).isEqualTo(longUsername);
    }

    @Test
    @DisplayName("empty username 가입 실패 테스트")
    void emptyUsernameSignupTest() {
        //given
        String emptyUsername = "";
        RequestMemberSignupDto emptyUsernameDto = createSignupDto(emptyUsername, password);

        //when
        ConstraintViolation<RequestMemberSignupDto> EmptyUsernameData = validator.validate(emptyUsernameDto).iterator().next();

        //then
        assertThat(EmptyUsernameData.getInvalidValue()).isEqualTo(emptyUsername);
    }

    @Test
    @DisplayName("blank username 가입 실패 테스트")
    void blankUsernameSignupTest() {
        //given
        String blankUsername = "       ";
        RequestMemberSignupDto blankUsernameDto = createSignupDto(blankUsername, password);

        //when
        ConstraintViolation<RequestMemberSignupDto> BlankUsernameData = validator.validate(blankUsernameDto).iterator().next();

        //then
        assertThat(BlankUsernameData.getInvalidValue()).isEqualTo(blankUsername);
    }

    @Test
    @DisplayName("8자 미만 비밀번호 가입 실패 테스트")
    void shortPasswordSignupTest() {
        //given
        String shortPassword = "Aa!1";
        RequestMemberSignupDto shortPasswordDto = createSignupDto(username, shortPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> shortPasswordData = validator.validate(shortPasswordDto).iterator().next();

        //then
        assertThat(shortPasswordData.getInvalidValue()).isEqualTo(shortPassword);
    }

    @Test
    @DisplayName("15자 초과 비밀번호 가입 실패 테스트")
    void longPasswordSignupTest() {
        //given
        String longPassword = "Aa!1aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        RequestMemberSignupDto longPasswordDto = createSignupDto(username, longPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> longPasswordData = validator.validate(longPasswordDto).iterator().next();

        //then
        assertThat(longPasswordData.getInvalidValue()).isEqualTo(longPassword);
    }

    @Test
    @DisplayName("대문자 누락 비밀번호 가입 실패 테스트")
    void unUppercasePasswordSignupTest() {
        //given
        String unUppercasePassword = "aa!1aaaaaa";
        RequestMemberSignupDto unUppercaseDto = createSignupDto(username, unUppercasePassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> unUppercasePasswordData = validator.validate(unUppercaseDto).iterator().next();

        //then
        assertThat(unUppercasePasswordData.getInvalidValue()).isEqualTo(unUppercasePassword);
    }

    @Test
    @DisplayName("소문자 누락 비밀번호 가입 실패 테스트")
    void unLowercasePasswordSignupTest() {
        //given
        String unLowercasePassword = "AA!1AAAAAA";
        RequestMemberSignupDto unLowercaseDto = createSignupDto(username, unLowercasePassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> unLowercasePasswordData = validator.validate(unLowercaseDto).iterator().next();

        //then
        assertThat(unLowercasePasswordData.getInvalidValue()).isEqualTo(unLowercasePassword);
    }

    @Test
    @DisplayName("숫자 누락 비밀번호 가입 실패 테스트")
    void unNumberPasswordSignupTest() {
        //given
        String unNumberPassword = "Aa!aaaaaaaa";
        RequestMemberSignupDto unNumberDto = createSignupDto(username, unNumberPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> unNumberPasswordData = validator.validate(unNumberDto).iterator().next();

        //then
        assertThat(unNumberPasswordData.getInvalidValue()).isEqualTo(unNumberPassword);
    }

    @Test
    @DisplayName("특수문자 누락 비밀번호 가입 실패 테스트")
    void unSpecialCharactersPasswordSignupTest() {
        //given
        String unSpecialCharactersPassword = "Aa1aaaaaaaa";
        RequestMemberSignupDto unSpecialCharactersDto = createSignupDto(username, unSpecialCharactersPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> unSpecialCharactersPasswordData = validator.validate(unSpecialCharactersDto).iterator().next();

        //then
        assertThat(unSpecialCharactersPasswordData.getInvalidValue()).isEqualTo(unSpecialCharactersPassword);
    }

    @Test
    @DisplayName("한글 포함 비밀번호 가입 실패 테스트")
    void koreanPasswordSignupTest() {
        //given
        String koreanPassword = "Aa1aaaa한글";
        RequestMemberSignupDto koreanDto = createSignupDto(username, koreanPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> koreanPasswordData = validator.validate(koreanDto).iterator().next();

        //then
        assertThat(koreanPasswordData.getInvalidValue()).isEqualTo(koreanPassword);
    }

    @Test
    @DisplayName("empty 비밀번호 가입 실패 테스트")
    void emptyPasswordSignupTest() {
        //given
        String emptyPassword = "";
        RequestMemberSignupDto emptyPasswordDto = createSignupDto(username, emptyPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> emptyPasswordData = validator.validate(emptyPasswordDto).iterator().next();

        //then
        assertThat(emptyPasswordData.getInvalidValue()).isEqualTo(emptyPassword);
    }

    @Test
    @DisplayName("blank 비밀번호 가입 실패 테스트")
    void blankPasswordSignupTest() {
        //given
        String blankPassword = "       ";
        RequestMemberSignupDto blankPasswordDto = createSignupDto(username, blankPassword);

        //when
        ConstraintViolation<RequestMemberSignupDto> blankPasswordData = validator.validate(blankPasswordDto).iterator().next();

        //then
        assertThat(blankPasswordData.getInvalidValue()).isEqualTo(blankPassword);
    }

    private RequestMemberSignupDto createSignupDto(String username, String password) {
        return RequestMemberSignupDto.builder()
                .username(username)
                .password(password)
                .build();
    }

}
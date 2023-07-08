package princeton.test.util.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.domain.role.MemberRole;

import static org.assertj.core.api.Assertions.*;

class MemberMapperTest {

    @Test
    @DisplayName("requestMemberSignupDto -> Member")
    void requestMemberSignupDtoToEntityTest() {
        //given
        String username = "username";
        String password = "aA1!password";

        RequestMemberSignupDto signupDto = RequestMemberSignupDto.builder()
                .username(username)
                .password(password)
                .build();

        //when
        Member member = MemberMapper.INSTANCE.requestMemberSignupDtoToEntity(signupDto);

        //then
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getPassword()).isEqualTo(password);
        assertThat(member.getRole()).isEqualTo(MemberRole.USER);
    }

}
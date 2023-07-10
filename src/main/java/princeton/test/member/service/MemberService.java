package princeton.test.member.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.member.domain.dto.request.RequestMemberLoginDto;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.repository.MemberRepository;
import princeton.test.util.JwtUtil;

import static princeton.test.util.mapper.MemberMapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${jwt.header.name}")
    private String headerName;

    public Long signup(RequestMemberSignupDto requestMemberSignupDto) {
        String username = requestMemberSignupDto.getUsername();
        if (isDuplicateUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 username입니다. username은 중복될 수 없습니다.");
        }

        Member member = INSTANCE.requestMemberSignupDtoToEntity(requestMemberSignupDto);
        member.encodedPassword(passwordEncoder);
        Member savedMember = memberRepository.save(member);

        return savedMember.getId();
    }

    public Long login(RequestMemberLoginDto requestMemberLoginDto, HttpServletResponse response) {
        String username = requestMemberLoginDto.getUsername();
        String password = requestMemberLoginDto.getPassword();
        Member member = validationUserInfo(username, password);
        String token = jwtUtil.createToken(username, member.getRole());

        response.addHeader(headerName, token);

        return member.getId();
    }

    private Member validationUserInfo(String username, String password) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("일치하는 username이 없습니다."));

        if (member.isInvalidPassword(password, passwordEncoder)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    private boolean isDuplicateUsername(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

}

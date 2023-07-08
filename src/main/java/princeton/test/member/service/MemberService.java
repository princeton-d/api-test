package princeton.test.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.repository.MemberRepository;

import static princeton.test.util.mapper.MemberMapper.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

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

    private boolean isDuplicateUsername(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

}

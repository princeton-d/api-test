package princeton.test.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import princeton.test.domain.dto.MemberResponseDto;

import static lombok.AccessLevel.*;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String pw;

    public Member(MemberResponseDto memberResponseDto) {
        this.id = memberResponseDto.getId();
        this.name = memberResponseDto.getName();
        this.email = memberResponseDto.getEmail();
        this.pw = memberResponseDto.getPw();
    }
}

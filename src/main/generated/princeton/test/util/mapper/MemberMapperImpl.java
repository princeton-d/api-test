package princeton.test.util.mapper;

import javax.annotation.processing.Generated;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.domain.role.MemberRole;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-07T23:21:26+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member requestMemberSignupDtoToEntity(RequestMemberSignupDto requestMemberSignupDto) {
        if ( requestMemberSignupDto == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.username( requestMemberSignupDto.getUsername() );
        member.password( requestMemberSignupDto.getPassword() );

        member.role( MemberRole.USER );

        return member.build();
    }
}

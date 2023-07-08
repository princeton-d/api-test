package princeton.test.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.domain.entity.Member;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(target = "role", constant = "USER")
    Member requestMemberSignupDtoToEntity(RequestMemberSignupDto requestMemberSignupDto);

}

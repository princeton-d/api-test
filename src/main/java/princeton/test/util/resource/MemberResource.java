package princeton.test.util.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import princeton.test.member.domain.dto.response.ResponseMemberDto;

import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class MemberResource extends RepresentationModel<MemberResource> {

    @JsonProperty("data")
    private List<ResponseMemberDto> responseMemberDtos;

}

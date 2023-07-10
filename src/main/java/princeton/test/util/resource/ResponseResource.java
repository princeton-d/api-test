package princeton.test.util.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

import static lombok.AccessLevel.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ResponseResource extends RepresentationModel<ResponseResource> {

    @JsonProperty("data")
    private List<?> responseMemberDtos;

}

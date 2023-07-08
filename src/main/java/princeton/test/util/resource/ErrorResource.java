package princeton.test.util.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import princeton.test.exception.dto.ResponseErrorDto;

@Getter
@AllArgsConstructor
public class ErrorResource extends RepresentationModel<ErrorResource> {

    @JsonProperty("data")
    private ResponseErrorDto data;

}

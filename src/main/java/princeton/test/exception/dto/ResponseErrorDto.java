package princeton.test.exception.dto;

import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ResponseErrorDto {

    private int status;
    private String error;
    private String message;

}

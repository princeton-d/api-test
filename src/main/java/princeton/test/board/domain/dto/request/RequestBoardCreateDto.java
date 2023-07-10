package princeton.test.board.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class RequestBoardCreateDto {

    @NotBlank(message = "제목은 비어있거나 공백일 수 없습니다.")
    private String title;

    @NotBlank
    private String username;

    @NotEmpty(message = "게시물 본문을 입력해주세요.")
    private String content;

}

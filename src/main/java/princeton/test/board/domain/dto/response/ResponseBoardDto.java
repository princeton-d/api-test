package princeton.test.board.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import princeton.test.comment.domain.dto.response.ResponseCommentDto;

import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
public class ResponseBoardDto {

    private String title;
    private String username;
    private String content;
    private Integer boardLikeCount;
    private List<ResponseCommentDto> comments;

}

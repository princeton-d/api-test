package princeton.test.comment.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import static lombok.AccessLevel.*;

@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
public class ResponseCommentDto {

    private String content;
    private String username;
    private Integer commentLikeCount;

    @QueryProjection
    public ResponseCommentDto(String content, String username, Integer commentLikeCount) {
        this.content = content;
        this.username = username;
        this.commentLikeCount = commentLikeCount;
    }
}
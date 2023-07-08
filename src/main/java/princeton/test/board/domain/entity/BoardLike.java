package princeton.test.board.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Entity @Getter
@NoArgsConstructor(access = PROTECTED)
public class BoardLike {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_like_id")
    private Long id;

    @NotNull
    @Column(name = "member_id")
    private Long memberId;

    @NotNull
    @Column(name = "board_id")
    private Long boardId;

}

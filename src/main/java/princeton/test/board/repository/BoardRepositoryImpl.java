package princeton.test.board.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import princeton.test.board.domain.dto.response.ResponseBoardDto;
import princeton.test.board.domain.entity.QBoard;

import java.util.List;

import static princeton.test.board.domain.entity.QBoard.*;
import static princeton.test.board.domain.entity.QBoardLike.*;
import static princeton.test.member.domain.entity.QMember.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResponseBoardDto> findResponseBoardDtos() {
        QBoard subBoard = new QBoard("subBoard");

        List<Tuple> boardTuple = queryFactory
                .select(
                        board.title,
                        member.username,
                        board.content
                )
                .from(board)
                .join(member).on(member.id.eq(board.memberId))
                .fetch();

        return null;
    }

}

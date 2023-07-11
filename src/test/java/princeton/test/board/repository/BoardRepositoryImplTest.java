package princeton.test.board.repository;

import com.querydsl.core.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.board.domain.entity.Board;
import princeton.test.board.domain.entity.BoardLike;
import princeton.test.board.domain.entity.QBoardLike;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.domain.role.MemberRole;
import princeton.test.member.repository.MemberRepository;

import java.util.List;

@SpringBootTest
@Transactional
class BoardRepositoryImplTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    BoardLikeRepository boardLikeRepository;

    @Test
    void findRequestBoardDtoTest() throws Exception {
        //given
        Member save = memberRepository.save(new Member(1L, "username", "aA1!password", MemberRole.USER));
        Long memberId = save.getId();
        Board savedBoard = boardRepository.save(new Board(2L, "title1", "content1", memberId));
        Long boardId = savedBoard.getId();
        boardRepository.save(new Board(3L, "title2", "content2", memberId));
        boardRepository.save(new Board(4L, "title3", "content3", memberId));
        boardRepository.save(new Board(5L, "title4", "content4", memberId));
        boardRepository.save(new Board(6L, "title5", "content5", memberId));

        boardLikeRepository.save(new BoardLike(1L, memberId, boardId));
        boardLikeRepository.save(new BoardLike(2L, memberId, boardId));
        boardLikeRepository.save(new BoardLike(3L, memberId, boardId));
        boardLikeRepository.save(new BoardLike(4L, memberId, boardId));
        boardLikeRepository.save(new BoardLike(5L, memberId, boardId));
        boardLikeRepository.save(new BoardLike(6L, memberId, boardId));
        boardLikeRepository.save(new BoardLike(7L, memberId, boardId));

        List<Tuple> boardTuple = boardRepository.getTuple();
        System.out.println(boardTuple);
        System.out.println("====================================");
        System.out.println("====================================");
        System.out.println(boardTuple.get(0).get(3, QBoardLike.class));
    }

}
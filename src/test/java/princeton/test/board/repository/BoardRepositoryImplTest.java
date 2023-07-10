package princeton.test.board.repository;

import com.querydsl.core.Tuple;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.board.domain.dto.response.ResponseBoardDto;
import princeton.test.board.domain.entity.Board;
import princeton.test.member.domain.entity.Member;
import princeton.test.member.domain.role.MemberRole;
import princeton.test.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

import static princeton.test.board.domain.entity.QBoard.board;
import static princeton.test.member.domain.entity.QMember.member;

@SpringBootTest
@Transactional
class BoardRepositoryImplTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void findRequestBoardDtoTest() throws Exception {
        //given
        memberRepository.save(new Member(1L, "username", "aA1!password", MemberRole.USER));
        boardRepository.save(new Board(2L, "title1", "content1", 1L));
        boardRepository.save(new Board(3L, "title2", "content2", 1L));
        boardRepository.save(new Board(4L, "title3", "content3", 1L));
        boardRepository.save(new Board(5L, "title4", "content4", 1L));
        boardRepository.save(new Board(6L, "title5", "content5", 1L));

        List<Tuple> boardTuple = boardRepository.getTuple();
        System.out.println(boardTuple);
    }

}
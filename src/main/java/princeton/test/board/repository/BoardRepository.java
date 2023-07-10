package princeton.test.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import princeton.test.board.domain.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardQueryRepository {
}

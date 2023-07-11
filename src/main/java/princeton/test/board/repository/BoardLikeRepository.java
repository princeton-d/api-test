package princeton.test.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import princeton.test.board.domain.entity.BoardLike;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
}

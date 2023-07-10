package princeton.test.board.repository;

import com.querydsl.core.Tuple;
import princeton.test.board.domain.dto.response.ResponseBoardDto;

import java.util.List;

public interface BoardQueryRepository {

    List<ResponseBoardDto> findResponseBoardDtos();
}

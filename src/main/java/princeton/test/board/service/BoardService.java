package princeton.test.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.board.domain.dto.request.RequestBoardCreateDto;
import princeton.test.board.domain.dto.response.ResponseBoardDto;
import princeton.test.board.repository.BoardRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public ResponseBoardDto createBoard(RequestBoardCreateDto requestDto) {
//        Board board = INSTANCE.requestBoardCreateDtoToEntity(requestDto);
//        Board savedBoard = boardRepository.save(board);

        return null;
    }

}

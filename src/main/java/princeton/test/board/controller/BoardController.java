package princeton.test.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import princeton.test.board.domain.dto.request.RequestBoardCreateDto;
import princeton.test.board.domain.dto.response.ResponseBoardDto;
import princeton.test.board.service.BoardService;
import princeton.test.util.resource.ResponseResource;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseResource> createBoard(RequestBoardCreateDto requestDto) {

        ResponseBoardDto responseBoardDto = boardService.createBoard(requestDto);

        return null;
    }

}

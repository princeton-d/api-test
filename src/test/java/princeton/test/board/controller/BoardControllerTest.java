package princeton.test.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import princeton.test.board.domain.dto.request.RequestBoardCreateDto;
import princeton.test.board.service.BoardService;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService boardService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "username", password = "aA1!password", roles = "USER")
    @DisplayName("게시물 등록 성공 테스트")
    void createBoardTest() throws Exception {
        //given
        RequestBoardCreateDto requestDto = RequestBoardCreateDto.builder()
                .title("title")
                .content("content")
                .username("username")
                .build();

        //when
        mockMvc.perform(post("/api/boards")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                )
                .andDo(print());

        //then
    }

}
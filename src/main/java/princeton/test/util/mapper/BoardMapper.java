package princeton.test.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import princeton.test.board.domain.dto.request.RequestBoardCreateDto;
import princeton.test.board.domain.entity.Board;

@Mapper
public interface BoardMapper {

    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);

//    Board requestBoardCreateDtoToEntity(RequestBoardCreateDto requestBoardCreateDto);

}

package princeton.test.exception.controller;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import princeton.test.exception.dto.ResponseErrorDto;
import princeton.test.member.controller.MemberController;
import princeton.test.util.resource.ErrorResource;

import java.util.Objects;

import static java.util.Objects.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResource> handleIllegalArgumentException(IllegalArgumentException exception) {
        ResponseErrorDto responseDto = ResponseErrorDto.builder()
                .status(400)
                .error("bad request")
                .message(exception.getMessage())
                .build();

        ErrorResource errorResource = new ErrorResource(responseDto);
        WebMvcLinkBuilder linkBuilder = linkTo(MemberController.class).slash("/signup");
        errorResource.add(linkBuilder.withRel("redirect"));

        return ResponseEntity.badRequest().body(errorResource);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ResponseErrorDto responseDto = ResponseErrorDto.builder()
                .status(400)
                .error("bad request")
                .message(requireNonNull(exception.getFieldError()).getDefaultMessage())
                .build();


        ErrorResource errorResource = new ErrorResource(responseDto);
        WebMvcLinkBuilder linkBuilder = linkTo(MemberController.class).slash("/signup");
        errorResource.add(linkBuilder.withRel("redirect"));

        return ResponseEntity.badRequest().body(errorResource);

    }

}

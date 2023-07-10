package princeton.test.member.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import princeton.test.member.domain.dto.request.RequestMemberLoginDto;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.service.MemberService;
import princeton.test.util.resource.ResponseResource;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseResource> signup(@RequestBody @Valid RequestMemberSignupDto requestDto) {
        Long savedId = memberService.signup(requestDto);
        ResponseResource responseResource = ResponseResource.builder()
                .responseMemberDtos(new ArrayList<>())
                .build();
        WebMvcLinkBuilder linkBuilder = linkTo(MemberController.class).slash(savedId);

        responseResource.add(linkBuilder.withSelfRel());
        responseResource.add(linkBuilder.withRel("updateMember"));
        responseResource.add(linkBuilder.withRel("deleteMember"));
        responseResource.add(linkTo(MemberController.class).slash("login").withRel("login"));

        return ResponseEntity.created(linkBuilder.toUri()).body(responseResource);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseResource> login(@RequestBody @Valid RequestMemberLoginDto requestDto, HttpServletResponse response) {
        Long memberId =  memberService.login(requestDto, response);
        WebMvcLinkBuilder linkBuilder = linkTo(MemberController.class).slash(memberId);
        ResponseResource responseResource = ResponseResource.builder()
                .responseMemberDtos(new ArrayList<>())
                .build();

        responseResource.add(linkBuilder.withSelfRel());
        responseResource.add(linkBuilder.withRel("updateMember"));
        responseResource.add(linkBuilder.withRel("deleteMember"));
        responseResource.add(linkBuilder.slash("logout").withRel("logout"));

        return ResponseEntity.ok(responseResource);
    }

}

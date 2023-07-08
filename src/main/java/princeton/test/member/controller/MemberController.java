package princeton.test.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import princeton.test.member.domain.dto.request.RequestMemberSignupDto;
import princeton.test.member.service.MemberService;
import princeton.test.util.resource.MemberResource;

import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResource> signup(@RequestBody @Valid RequestMemberSignupDto requestDto) {
        Long savedId = memberService.signup(requestDto);
        MemberResource memberResource = MemberResource.builder()
                .responseMemberDtos(new ArrayList<>())
                .build();
        WebMvcLinkBuilder linkBuilder = linkTo(MemberController.class).slash(savedId);

        memberResource.add(linkBuilder.withSelfRel());
        memberResource.add(linkBuilder.withRel("updateMember"));
        memberResource.add(linkBuilder.withRel("deleteMember"));
        memberResource.add(linkTo(MemberController.class).slash("login").withRel("login"));

        return ResponseEntity.created(linkBuilder.toUri()).body(memberResource);
    }

}

package com.room.yeahnolja.domain.member.controller;

import com.room.yeahnolja.domain.common.CommonResponse;
import com.room.yeahnolja.domain.member.dto.JoinRequestDto;
import com.room.yeahnolja.domain.member.dto.LoginRequestDto;
import com.room.yeahnolja.domain.member.entity.Member;
import com.room.yeahnolja.domain.member.service.MemberService;
import com.room.yeahnolja.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Member Controller")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<Member> join(@Valid @RequestBody JoinRequestDto joinRequestDto) {
        log.info("[컨트롤러] 회원가입 시, 이메일 : {}", joinRequestDto.getEmail());

        return ResponseEntity.ok()
                .body(memberService.join(joinRequestDto));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<?>> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        Member member = memberService.login(loginRequestDto);
        if (member != null) {
            String token = jwtTokenProvider.createToken(member.getUsername(), member.getAuthorities());
            CommonResponse<String> response = CommonResponse.onSuccess(200, token);  // 성공 응답 생성
            return ResponseEntity.ok(response);
        } else {
            CommonResponse<String> errorResponse = CommonResponse.<String>builder()  // 실패 응답 생성
                    .code(HttpStatus.UNAUTHORIZED.value())
                    .success(false)
                    .message("로그인 실패")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}

package com.room.yeahnolja.domain.member.controller;

import com.room.yeahnolja.domain.member.dto.JoinRequestDto;
import com.room.yeahnolja.domain.member.dto.LoginRequestDto;
import com.room.yeahnolja.domain.member.entity.Member;
import com.room.yeahnolja.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member Controller")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<Member> join(@RequestBody JoinRequestDto joinRequestDto) {
        log.info("[컨트롤러] 회원가입 시, 이메일 : {}", joinRequestDto.getEmail());

        return ResponseEntity.ok()
                .body(memberService.join(joinRequestDto));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody LoginRequestDto loginRequestDto) {
        Member member = memberService.login(loginRequestDto.getEmail(), loginRequestDto.getPwd());
        return ResponseEntity.ok(member);
    }
}

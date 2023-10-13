package com.room.yeahnolja.domain.member.service;

import com.room.yeahnolja.domain.member.dto.JoinRequestDto;
import com.room.yeahnolja.domain.member.entity.Member;
import com.room.yeahnolja.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(JoinRequestDto joinRequestDto) {
        log.info("[서비스] 회원가입 로직 시작");
        Member member = new Member();
        member.setEmail(joinRequestDto.getEmail());
        member.setPwd(joinRequestDto.getPwd());
        member.setRole(joinRequestDto.getRole());

        Member memberEntity = memberRepository.save(member);
        log.info("[서비스] 회원가입 로직 끝");
        return memberEntity;
    }

    public Member login(String email, String pwd) {
        log.info("[서비스] 로그인 로직 시작");
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));
        if (!member.getPwd().equals(pwd)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        log.info("[서비스] 로그인 로직 끝");
        return member;
    }

}

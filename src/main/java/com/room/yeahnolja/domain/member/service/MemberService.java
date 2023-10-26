package com.room.yeahnolja.domain.member.service;

import com.room.yeahnolja.domain.member.dto.JoinRequestDto;
import com.room.yeahnolja.domain.member.dto.LoginRequestDto;
import com.room.yeahnolja.domain.member.entity.Member;
import com.room.yeahnolja.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(JoinRequestDto joinRequestDto) {
        log.info("[서비스] 회원가입 로직 시작");
        Member member = new Member();
        member.setEmail(joinRequestDto.getEmail());
        member.setPwd(passwordEncoder.encode(joinRequestDto.getPwd()));
        member.setRole(joinRequestDto.getRole());

        Member memberEntity = memberRepository.save(member);
        log.info("[서비스] 회원가입 로직 끝");
        return memberEntity;
    }

    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username);
    }

    public Member login(LoginRequestDto loginRequestDto) {
        log.info("[서비스] 로그인 로직 시작");
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail());
        String rawPassword = loginRequestDto.getPwd(); //사용자가 입력한 비밀번호
        String encodedPassword = member.getPassword(); //디비에 저장된 해시된 비밀번호
        if (passwordEncoder.matches(rawPassword, encodedPassword)) {
            log.info("[서비스] 로그인 로직 끝");
            return member;
        } else {
            log.info("[서비스] 로그인 로직 끝");
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
    }
}

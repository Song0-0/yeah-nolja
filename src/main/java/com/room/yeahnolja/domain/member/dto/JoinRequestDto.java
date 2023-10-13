package com.room.yeahnolja.domain.member.dto;

import lombok.Getter;

@Getter
public class JoinRequestDto {
    private String email;
    private String pwd;
    private String role;
}

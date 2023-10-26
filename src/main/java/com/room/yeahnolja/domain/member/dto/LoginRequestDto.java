package com.room.yeahnolja.domain.member.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class LoginRequestDto {
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    @NotBlank(message = "이메일은 비워둘 수 없습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 비워둘 수 없습니다.")
    @Size(min = 4, max = 50, message = "비밀번호는 4자 이상 50자 이하이어야합니다.")
    private String pwd;
}

package com.room.yeahnolja.domain.hotel.dto;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class HotelCreateRequestDto {

    @NotBlank(message = "호텔명은 필수 항목입니다.")
    private String name;
    @NotBlank(message = "호텔 타입은 필수 항목입니다.")
    private String type;
    @NotBlank(message = "호텔 주소는 필수 항목입니다.")
    private String address;
    @NotBlank(message = "호텔 연락처는 필수 항목입니다.")
    private String contact;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    @Min(value = 1, message = "호텔 등급은 1~5중 하나로, 필수 항목입니다.")
    @Max(value = 5, message = "호텔 등급은 1~5중 하나로, 필수 항목입니다.")
    private int star;
    @NotBlank(message = "호텔 소개는 필수 항목입니다.")
    private String description;
    @Min(value=1, message = "호텔 객실 수는 1이상으로, 필수 항목입니다.")
    private int rooms;
}

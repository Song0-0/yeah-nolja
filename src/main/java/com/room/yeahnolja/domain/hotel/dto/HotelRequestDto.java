package com.room.yeahnolja.domain.hotel.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRequestDto {
    /** TODO:
     * 숫자형식이라 부분 수정시, 수정값에 넣지 않아도 숫자는 다 0으로 수정이되었다.그래서 Wrapper클래스 사용
     */
    private String name;
    private String type;
    private String address;
    private String phone;
    private String email;
    private Integer star;
    private String description;
    private Integer minPrice;
    private Integer maxPrice;
    private String availabilityId;
    private Integer facilitiesId;
    private Integer rooms;
    private Integer imageId;

    private int id;
}

package com.room.yeahnolja.hotel;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelMapper {
    void insertHotel(HotelRequestDto requestDto);
}

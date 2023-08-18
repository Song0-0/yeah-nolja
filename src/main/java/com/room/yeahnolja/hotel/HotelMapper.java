package com.room.yeahnolja.hotel;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    void insertHotel(HotelRequestDto requestDto);
    List<HotelResponseDto> selectAllHotels();
}

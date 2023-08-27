package com.room.yeahnolja.domain.hotel.mapper;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelMapper {
    boolean insertHotel(HotelRequestDto requestDto);

    List<HotelResponseDto> selectAllHotels();

    HotelResponseDto selectHotel(int hotelId);

    List<HotelResponseDto> selectHotelsByLocation(String location);

    List<HotelResponseDto> selectHotelsByPrice(int price);

    void updateHotel(HotelRequestDto requestDto);

    void deleteHotel(int hotelId);

}

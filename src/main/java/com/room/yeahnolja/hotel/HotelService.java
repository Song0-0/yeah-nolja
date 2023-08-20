package com.room.yeahnolja.hotel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;

    public void saveHotel(HotelRequestDto requestDto) {
        hotelMapper.insertHotel(requestDto);
    }

    public List<HotelResponseDto> getAllHotels() {
        return hotelMapper.selectAllHotels();
    }

    public HotelResponseDto getHotel(int hotelId) {
        return hotelMapper.selectHotel(hotelId);
    }

    public List<HotelResponseDto> getHotelsByLocation(String location) {
        return hotelMapper.selectHotelsByLocation(location);
    }

    public List<HotelResponseDto> getHotelsByPrice(int price) {
        return hotelMapper.selectHotelsByPrice(price);
    }

    public void modifyHotel(int hotelId, HotelRequestDto requestDto) {
        requestDto.setId(hotelId);
        hotelMapper.updateHotel(requestDto);
    }

    public void removeHotel(int hotelId) {
        hotelMapper.deleteHotel(hotelId);
    }
}

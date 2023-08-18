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
}

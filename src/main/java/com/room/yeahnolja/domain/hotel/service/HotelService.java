package com.room.yeahnolja.domain.hotel.service;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.mapper.HotelMapper;
import com.room.yeahnolja.domain.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;

    public void saveHotel(HotelRequestDto requestDto) {
        hotelMapper.insertHotel(requestDto);
    }

    public void save(Hotel hotel) {
        hotelRepository.save(hotel);
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

    private HotelResponseDto convertToDto(Hotel hotel) {
        HotelResponseDto dto = new HotelResponseDto();
        dto.setId(hotel.getId());
        dto.setName(hotel.getName());
        dto.setType(hotel.getType());
        dto.setAddress(hotel.getAddress());
        dto.setPhone(hotel.getPhone());
        dto.setEmail(hotel.getEmail());
        dto.setStar(hotel.getStar());
        dto.setDescription(hotel.getDescription());
        dto.setMinPrice(hotel.getMin_price());
        dto.setMaxPrice(hotel.getMax_price());
        dto.setAvailabilityId(hotel.getAvailability_id());
        dto.setFacilitiesId(hotel.getFacilities_id());
        dto.setRooms(hotel.getRooms());
        dto.setImageId(hotel.getImage_id());
        dto.setRegDt(hotel.getReg_dt());
        dto.setModDt(hotel.getMod_dt());
        return dto;
    }
    public List<HotelResponseDto> getHotelsByName(String name) {
        List<Hotel> hotels = hotelRepository.findByNameContaining(name);
        return hotels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

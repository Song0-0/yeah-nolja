package com.room.yeahnolja.domain.hotel.service;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    //    private final HotelMapper hotelMapper;
//    private final HotelJpaRepository hotelJpaRepository;
    private final HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return hotel;
    }

    public HotelResponseDto modifyHotel(int hotelId, HotelRequestDto requestDto, int id, String name, String type, String address, String phone, int star, String description, int min_price, int max_price) {
        requestDto.setId(hotelId);
        return hotelRepository.update(id, name, type, address, phone, star, description, min_price, max_price);
    }

    public void removeHotel(int hotelId) {
        hotelRepository.delete(hotelId);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotel(int hotelId) {
        return hotelRepository.findById(hotelId);
    }

    public Optional<Hotel> getHotelsByLocation(String location) {
        return hotelRepository.findAllByLocation(location);
    }

    public Optional<Hotel> getHotelsByPrice(int price) {
        return hotelRepository.findAllByPrice(price);
    }

    /**
     * TODO:
     * MyBatis를 통해서 그럼 patch를 활용하기 위해 사용자가 요청하는 값만을 매개변수에 담을 수는 없는 것인가?
     * 그래서 patch를 쓰더라도 xml에 분기문을 작성해주거나
     * service에서 로직으로 requestDto가 null이 아닐때 그 값을 새로 updateDto를 만들어서 updateDto안에 필드들에 담아서 그 담은 것을 mapper에 넘겨줘서 처리해야하는 건가?
     */

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
        Optional<Hotel> hotels = hotelRepository.findAllByName(name);
        return hotels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

package com.room.yeahnolja.domain.hotel.service;

import com.room.yeahnolja.domain.hotel.dto.HotelRequestDto;
import com.room.yeahnolja.domain.hotel.dto.HotelResponseDto;
import com.room.yeahnolja.domain.hotel.entity.Hotel;
import com.room.yeahnolja.domain.hotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    //    private final HotelMapper hotelMapper;
//    private final HotelJpaRepository hotelJpaRepository;
    private final HotelRepository hotelRepository;


    public HotelResponseDto saveHotel(HotelRequestDto requestDto) {
        Hotel hotel = new Hotel();
        hotel.setName(requestDto.getName());
        hotel.setType(requestDto.getType());
        hotel.setAddress(requestDto.getAddress());
        hotel.setPhone(requestDto.getPhone());
        hotel.setEmail(requestDto.getEmail());
        hotel.setStar(requestDto.getStar());
        hotel.setDescription(requestDto.getDescription());
        hotel.setMinPrice(requestDto.getMinPrice());
        hotel.setMaxPrice(requestDto.getMaxPrice());
        hotel.setAvailabilityId(requestDto.getAvailabilityId());
        hotel.setFacilitiesId(requestDto.getFacilitiesId());
        hotel.setRooms(requestDto.getRooms());
        hotel.setImageId(requestDto.getImageId());

        Hotel hotelDto = hotelRepository.save(hotel);

        return new HotelResponseDto(hotelDto);
    }

    private void modifyStringIfNotNull(String value, Consumer<String> modifier) {
        if (StringUtils.hasText(value)) {
            modifier.accept(value);
        }
    }

    private void modifyIntIfNotZero(int value, Consumer<Integer> modifier) {
        if (value != 0) {
            modifier.accept(value);
        }
    }

    public HotelResponseDto modifyHotel(int hotelId, HotelRequestDto requestDto) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id " + hotelId));

        modifyStringIfNotNull(requestDto.getName(), hotel::setName);
        modifyStringIfNotNull(requestDto.getType(), hotel::setType);
        modifyStringIfNotNull(requestDto.getAddress(), hotel::setAddress);
        modifyStringIfNotNull(requestDto.getPhone(), hotel::setPhone);
        modifyStringIfNotNull(requestDto.getEmail(), hotel::setEmail);
        modifyStringIfNotNull(requestDto.getDescription(), hotel::setDescription);
        modifyStringIfNotNull(requestDto.getAvailabilityId(), hotel::setAvailabilityId);

        modifyIntIfNotZero(requestDto.getStar(), hotel::setStar);
        modifyIntIfNotZero(requestDto.getMinPrice(), hotel::setMinPrice);
        modifyIntIfNotZero(requestDto.getMaxPrice(), hotel::setMaxPrice);
        modifyIntIfNotZero(requestDto.getFacilitiesId(), hotel::setFacilitiesId);
        modifyIntIfNotZero(requestDto.getRooms(), hotel::setRooms);
        modifyIntIfNotZero(requestDto.getImageId(), hotel::setImageId);

        Hotel update = hotelRepository.update(hotelId, hotel);

        return new HotelResponseDto(update);
    }

    public void removeHotel(int hotelId) {
        hotelRepository.delete(hotelId);
    }

    public List<HotelResponseDto> getAllHotels() {
        List<Hotel> allHotels = hotelRepository.findAll();
        List<HotelResponseDto> allHotelResponseDto = allHotels.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
        return allHotelResponseDto;
    }

    public HotelResponseDto getHotel(int hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id " + hotelId));
        return new HotelResponseDto(hotel);
    }

    public List<HotelResponseDto> getHotelsByLocation(String location) {
        List<Hotel> allByLocation = hotelRepository.findAllByLocation(location);
        return allByLocation.stream()
                .map(HotelResponseDto::new)
                .collect(Collectors.toList());
    }


    public List<HotelResponseDto> getHotelsByPrice(int price) {
        List<Hotel> allByPrice = hotelRepository.findAllByPrice(price);
        return allByPrice.stream()
                .map(HotelResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<HotelResponseDto> getHotelsByName(String name) {
        List<Hotel> allByName = hotelRepository.findAllByName(name);
        return allByName.stream()
                .map(hotel -> new HotelResponseDto(hotel))
                .collect(Collectors.toList());
    }
}

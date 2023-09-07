package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.*;

//@Repository
//@Primary
public class HotelMapRepository implements HotelRepository {
    private static int id = 1;

    private Map<Integer, Hotel> db = new HashMap<>();

    @PostConstruct
    void init() {
        db.put(id, Hotel.builder()
                .id(1)
                .name("힐튼1")
                .type("관광호텔업")
                .address("서울시")
                .phone("010-4564-4564")
                .email("hilton@hiilton.com")
                .star(5)
                .description("힐튼 설명")
                .minPrice(500000)
                .maxPrice(1500000)
                .availabilityId("Y")
                .facilitiesId(1)
                .rooms(250)
                .imageId(1)
                .regDt(LocalDateTime.of(2023, 9, 3, 10, 20, 30))
                .modDt(LocalDateTime.of(2023, 9, 6, 10, 20, 30))
                .build());
        id++;

        db.put(id, Hotel.builder()
                .id(2)
                .name("경원재 앰배서더 인천")
                .type("한국전통호텔업")
                .address("인천광역시")
                .phone("010-4564-4564")
                .email("incheonhotel@hotel.com")
                .star(5)
                .description("경원재 설명")
                .minPrice(800000)
                .maxPrice(2000000)
                .availabilityId("Y")
                .facilitiesId(2)
                .rooms(300)
                .imageId(2)
                .regDt(LocalDateTime.of(2023, 9, 2, 10, 20, 30))
                .modDt(LocalDateTime.of(2023, 9, 5, 10, 20, 30))
                .build());
        id++;
    }

    //호텔 단건 생성
    public Hotel save(Hotel hotel) {
        hotel.setId(id);
        db.put(id, hotel);
        id++;
        return hotel;
    }

    //호텔 단건 수정
    public Hotel update(int id, Hotel hotel) {
        Hotel hotelEntity = db.put(id, hotel);
        return hotelEntity;
    }

    //호텔 단건 삭제
    public void delete(int id) {
        db.remove(id);
    }

    //호텔 전체 조회
    public List<Hotel> findAll() {
        List<Hotel> values = new ArrayList<>(db.values());
        return values;
    }

    //호텔 단건 조회
    public Optional<Hotel> findById(int id) {
        Hotel hotel = db.get(id);
        return Optional.ofNullable(hotel);
    }

    //특정 가격에 대한 호텔 조회
    public List<Hotel> findAllByPrice(int price) {
        List<Hotel> hotels = new ArrayList<>();
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (price >= entrySet.getValue().getMinPrice() && price <= entrySet.getValue().getMaxPrice()) {
                hotels.add(entrySet.getValue());
            }
        }
        return hotels;
    }

    //특정 호텔명에 대한 호텔 조회
    public List<Hotel> findAllByName(String name) {
        List<Hotel> hotels = new ArrayList<>();
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (entrySet.getValue().getName().contains(name)) {
                hotels.add(entrySet.getValue());
            }
        }
        return hotels;
    }

    //특정 지역에 대한 호텔 조회
    public List<Hotel> findAllByLocation(String address) {
        List<Hotel> hotels = new ArrayList<>();
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (entrySet.getValue().getAddress().contains(address)) {
                hotels.add(entrySet.getValue());
            }
        }
        return hotels;
    }

//    public static void main(String[] args) {
//        HotelMapRepository hotelMapRepository = new HotelMapRepository();
//
//        Hotel hotel1 = new Hotel();
//        hotel1.setName("이름1");
//        hotel1.setType("타입1");
//        hotel1.setAddress("주소1");
//        hotel1.setPhone("010-1231-12411");
//        hotel1.setStar(51);
//        hotel1.setDescription("설명설명1");
//        hotel1.setMin_price(3000001);
//        hotel1.setMax_price(10000001);
//        System.out.println("==데이터생성1==");
//        hotelMapRepository.save(hotel1);
//
//        Hotel hotel2 = new Hotel();
//        hotel2.setName("이름2");
//        hotel2.setType("타입2");
//        hotel2.setAddress("주소2");
//        hotel2.setPhone("010-1231-12412");
//        hotel2.setStar(52);
//        hotel2.setDescription("설명설명2");
//        hotel2.setMin_price(3000002);
//        hotel2.setMax_price(10000002);
//        System.out.println("==데이터생성2==");
//        hotelMapRepository.save(hotel2);
//
//        Hotel hotel3 = new Hotel();
//        hotel3.setName("이름3");
//        hotel3.setType("타입3");
//        hotel3.setAddress("주소3");
//        hotel3.setPhone("010-1231-12413");
//        hotel3.setStar(53);
//        hotel3.setDescription("설명설명3");
//        hotel3.setMin_price(3000003);
//        hotel3.setMax_price(10000003);
//        System.out.println("==데이터생성3==");
//        hotelMapRepository.save(hotel3);
//
//        System.out.println("==전체조회==");
//        hotelMapRepository.findAll();
//
//        System.out.println("==단건조회==");
//        hotelMapRepository.findById(2);
//
//        System.out.println("==데이터 업데이트==");
//        hotelMapRepository.update(2, "2수정이름", "2수정타입", "2수정주소", "2010-23123-1241", 25, "2수정설명", 500000, 1500000);
//
//        System.out.println("==단건조회==");
//        hotelMapRepository.findById(2);
//
//        System.out.println("==데이터삭제==");
//        hotelMapRepository.delete(1);
//
//        System.out.println("==전체조회==");
//        hotelMapRepository.findAll();
//
//        System.out.println("==특정 가격에 대한 호텔 조회==");
//        hotelMapRepository.findAllByPrice(1400000);
//
//        System.out.println("==특정 호텔명에 대한 호텔 조회");
//        hotelMapRepository.findAllByName("이름");
//
//        System.out.println("==특정 지역에 대한 호텔 조회");
//        hotelMapRepository.findAllByLocation("주소");
//    }
}

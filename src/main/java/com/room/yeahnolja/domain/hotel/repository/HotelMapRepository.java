package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//@Repository
//@Primary
public class HotelMapRepository {
    public static void main(String[] args) {
        HotelMapRepository hotelMapRepository = new HotelMapRepository();

        Hotel hotel1 = new Hotel();
        hotel1.setName("이름1");
        hotel1.setType("타입1");
        hotel1.setAddress("주소1");
        hotel1.setPhone("010-1231-12411");
        hotel1.setStar(51);
        hotel1.setDescription("설명설명1");
        hotel1.setMin_price(3000001);
        hotel1.setMax_price(10000001);
        System.out.println("==데이터생성1==");
        hotelMapRepository.save(hotel1);

        Hotel hotel2 = new Hotel();
        hotel2.setName("이름2");
        hotel2.setType("타입2");
        hotel2.setAddress("주소2");
        hotel2.setPhone("010-1231-12412");
        hotel2.setStar(52);
        hotel2.setDescription("설명설명2");
        hotel2.setMin_price(3000002);
        hotel2.setMax_price(10000002);
        System.out.println("==데이터생성2==");
        hotelMapRepository.save(hotel2);

        Hotel hotel3 = new Hotel();
        hotel3.setName("이름3");
        hotel3.setType("타입3");
        hotel3.setAddress("주소3");
        hotel3.setPhone("010-1231-12413");
        hotel3.setStar(53);
        hotel3.setDescription("설명설명3");
        hotel3.setMin_price(3000003);
        hotel3.setMax_price(10000003);
        System.out.println("==데이터생성3==");
        hotelMapRepository.save(hotel3);

        System.out.println("==전체조회==");
        hotelMapRepository.findAll();

        System.out.println("==단건조회==");
        hotelMapRepository.findAllById(2);

        System.out.println("==데이터 업데이트==");
        hotelMapRepository.update(2, "2수정이름", "2수정타입", "2수정주소", "2010-23123-1241", 25, "2수정설명", 500000, 1500000);

        System.out.println("==단건조회==");
        hotelMapRepository.findAllById(2);

        System.out.println("==데이터삭제==");
        hotelMapRepository.delete(1);

        System.out.println("==전체조회==");
        hotelMapRepository.findAll();

        System.out.println("==특정 가격에 대한 호텔 조회==");
        hotelMapRepository.findAllByPrice(1400000);

        System.out.println("==특정 호텔명에 대한 호텔 조회");
        hotelMapRepository.findAllByName("이름");

        System.out.println("==특정 지역에 대한 호텔 조회");
        hotelMapRepository.findAllByLocation("주소");
    }

    private static int id = 1;

    private Map<Integer, Hotel> db = new HashMap<>();

    //호텔 단건 생성
    public void save(Hotel hotel) {
        db.put(id, hotel);
        id++;
    }

    //호텔 전체 조회
    public Collection<Hotel> findAll() {
        Collection<Hotel> values = db.values();

        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            System.out.println(entrySet.getKey() + ":" + entrySet.getValue().toString());
        }

        return values;
    }

    //호텔 단건 조회
    public void findAllById(int id) {
        Hotel hotel = db.get(id);
        System.out.println(hotel.toString());
    }

    //호텔 단건 수정
    public void update(int id, String name, String type, String address, String phone, int star, String description, int min_price, int max_price) {
        Hotel hotel = db.get(id);

        hotel.setName(name);
        hotel.setType(type);
        hotel.setAddress(address);
        hotel.setPhone(phone);
        hotel.setStar(star);
        hotel.setDescription(description);
        hotel.setMin_price(min_price);
        hotel.setMax_price(max_price);

        db.put(id, hotel);
    }

    //호텔 단건 삭제
    public void delete(int id) {
        db.remove(id);
    }


    //특정 가격에 대한 호텔 조회
    public void findAllByPrice(int price) {
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (price >= entrySet.getValue().getMin_price() && price <= entrySet.getValue().getMax_price()) {
                Hotel hotel = db.get(entrySet.getKey());
                System.out.println(hotel.toString());
            }
        }
    }

    //특정 호텔명에 대한 호텔 조회
    public void findAllByName(String name) {
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (entrySet.getValue().getName().contains(name)) {
                Hotel hotel = db.get(entrySet.getKey());
                System.out.println(hotel.toString());
            }
        }
    }

    //특정 지역에 대한 호텔 조회
    public void findAllByLocation(String address) {
        for (Map.Entry<Integer, Hotel> entrySet : db.entrySet()) {
            if (entrySet.getValue().getAddress().contains(address)) {
                Hotel hotel = db.get(entrySet.getKey());
                System.out.println(hotel.toString());
            }
        }
    }
}

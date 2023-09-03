package com.room.yeahnolja.domain.hotel.repository;

import com.room.yeahnolja.domain.hotel.entity.Hotel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class HotelJpaRepositoryClass implements HotelRepository {

    private final EntityManager em;

    public HotelJpaRepositoryClass(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    /**
     * TODO:
     * No EntityManager with actual transaction available for current thread - cannot reliably process 'persist' call 오류는 보통 트랜잭션이 시작되지 않았을 때 발생합니다.
     * 이런 오류가나서 @Transactional 을 해줬다..
     */
    public Hotel save(Hotel hotel) {
        em.persist(hotel);
        return hotel;
    }

    @Override
    @Transactional
    public Hotel update(int id, Hotel hotel) {
        em.persist(hotel);
        Hotel hotelEntity = em.find(Hotel.class, id);
        return hotelEntity;
    }

    @Override
    @Transactional
    public void delete(int id) {
        Hotel hotel = em.find(Hotel.class, id);
        if (hotel != null) {
            em.remove(hotel);
        }
    }

    @Override
    public List<Hotel> findAll() {
        return em.createQuery("select h from Hotel h", Hotel.class)
                .getResultList();
    }

    @Override
    public Optional<Hotel> findById(int id) {
        Hotel hotel = em.find(Hotel.class, id);
        return Optional.ofNullable(hotel);
    }

    @Override
    public List<Hotel> findAllByLocation(String address) {
        List<Hotel> result = em.createQuery("select h from Hotel h where h.address like :address", Hotel.class)
                .setParameter("address", "%" + address + "%")
                .getResultList();
        return result;
    }

    @Override
    public List<Hotel> findAllByPrice(int price) {
        List<Hotel> result = em.createQuery("select h from Hotel " + "h where h.min_price <= :price and h.max_price >= :price")
                .setParameter("price", price)
                .getResultList();
        return result;
    }

    @Override
    public List<Hotel> findAllByName(String name) {
        List<Hotel> result = em.createQuery("select h from Hotel " + "h where h.name like :name", Hotel.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
        return result;
    }

}

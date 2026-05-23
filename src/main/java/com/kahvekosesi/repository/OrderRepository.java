package com.kahvekosesi.repository;

import com.kahvekosesi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByRestaurantTableId(Long tableId);

    List<Order> findByQuantityGreaterThan(Integer quantity);

    void deleteByRestaurantTableId(Long tableId);
}
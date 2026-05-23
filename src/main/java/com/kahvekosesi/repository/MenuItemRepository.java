package com.kahvekosesi.repository;

import com.kahvekosesi.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByCategory(String category);

    List<MenuItem> findByPriceLessThan(Double price);

    List<MenuItem> findByNameContainingIgnoreCase(String name);
}
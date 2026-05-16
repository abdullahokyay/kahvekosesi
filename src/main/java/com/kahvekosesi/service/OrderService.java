package com.kahvekosesi.service;

import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.entity.RestaurantTable;
import com.kahvekosesi.repository.MenuItemRepository;
import com.kahvekosesi.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepository.findAll();
    }

    public void occupyTable(Long tableId) {
        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı"));
        table.setOccupied(true);
        restaurantTableRepository.save(table);
    }

    public void checkoutTable(Long tableId) {
        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı "));
        table.setOccupied(false);
        restaurantTableRepository.save(table);
    }
}
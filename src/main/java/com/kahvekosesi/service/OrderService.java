package com.kahvekosesi.service;

import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.entity.Order;
import com.kahvekosesi.entity.RestaurantTable;
import com.kahvekosesi.repository.MenuItemRepository;
import com.kahvekosesi.repository.OrderRepository;
import com.kahvekosesi.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final OrderRepository orderRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepository.findAll();
    }

    public void occupyTable(Long tableId) {
        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı!"));
        table.setOccupied(true);
        restaurantTableRepository.save(table);
    }

    public void addOrderToTable(Long tableId, Long menuItemId, Integer quantity) {
        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı!"));
        if (!table.isOccupied()) {
            table.setOccupied(true);
            restaurantTableRepository.save(table);
        }

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı!"));

        Order order = new Order(null, table, menuItem, quantity);
        orderRepository.save(order);
    }


    public Double calculateTotal(Long tableId) {
        List<Order> orders = orderRepository.findByRestaurantTableId(tableId);
        return orders.stream()
                .mapToDouble(order -> order.getMenuItem().getPrice() * order.getQuantity())
                .sum();
    }

    @Transactional
    public void checkoutTable(Long tableId) {
        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Masa bulunamadı!"));

        table.setOccupied(false);
        restaurantTableRepository.save(table);
        orderRepository.deleteByRestaurantTableId(tableId);
    }
}
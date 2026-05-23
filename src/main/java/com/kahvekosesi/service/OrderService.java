package com.kahvekosesi.service;

import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.entity.Order;
import com.kahvekosesi.entity.RestaurantTable;
import com.kahvekosesi.exception.ResourceNotFoundException;
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

    private static Double dailyTotalSales = 0.0;

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public List<RestaurantTable> getAllTables() {
        return restaurantTableRepository.findAll();
    }

    public void occupyTable(Long tableId) {

        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Masa bulunamadı!"));

        table.setOccupied(true);
        restaurantTableRepository.save(table);
    }

    public void addOrderToTable(Long tableId,
                                Long menuItemId,
                                Integer quantity) {

        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Masa bulunamadı!"));

        if (!table.isOccupied()) {
            table.setOccupied(true);
            restaurantTableRepository.save(table);
        }

        MenuItem menuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ürün bulunamadı!"));

        Order order = new Order(null, table, menuItem, quantity);
        orderRepository.save(order);
    }

    public Double calculateTotal(Long tableId) {

        List<Order> orders = orderRepository.findByRestaurantTableId(tableId);

        return orders.stream()
                .mapToDouble(order ->
                        order.getMenuItem().getPrice() * order.getQuantity())
                .sum();
    }
    public int getOrderCountForTable(Long tableId) {
        return orderRepository.findByRestaurantTableId(tableId).size();
    }

    public Double getTableTotal(Long tableId) {
        return calculateTotal(tableId);
    }

    public List<Order> getOrdersByTable(Long tableId) {
        return orderRepository.findByRestaurantTableId(tableId);
    }

    @Transactional
    public void checkoutTable(Long tableId) {

        RestaurantTable table = restaurantTableRepository.findById(tableId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Masa bulunamadı!"));

        Double tableTotal = calculateTotal(tableId);
        dailyTotalSales += tableTotal;

        table.setOccupied(false);
        restaurantTableRepository.save(table);

        orderRepository.deleteByRestaurantTableId(tableId);
    }

    public Double getDailyTotalSales() {
        return dailyTotalSales;
    }

    public void saveMenuItem(MenuItem menuItem) {
        menuItemRepository.save(menuItem);
    }

    public void updateProductPrice(Long id, Double newPrice) {

        MenuItem item = menuItemRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ürün bulunamadı!"));

        item.setPrice(newPrice);

        menuItemRepository.save(item);
    }
}
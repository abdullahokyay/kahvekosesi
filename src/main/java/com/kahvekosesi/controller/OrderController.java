package com.kahvekosesi.controller;

import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.entity.RestaurantTable;
import com.kahvekosesi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/menu")
    public ResponseEntity<List<MenuItem>> getMenu() {
        return ResponseEntity.ok(orderService.getAllMenuItems());

    }
    @GetMapping("/tables")
    public ResponseEntity<List<RestaurantTable>> getTables() {
        return ResponseEntity.ok(orderService.getAllTables());
    }

    @PostMapping("/tables/{id}/occupy")
    public ResponseEntity<String> occupyTable(@PathVariable Long id) {
        orderService.occupyTable(id);
        return ResponseEntity.ok(id + " numaralı masa başarıyla açıldı, afiyet olsun kanki!");
    }

    @PostMapping("/tables/{id}/checkout")
    public ResponseEntity<String> checkoutTable(@PathVariable Long id) {
        orderService.checkoutTable(id);
        return ResponseEntity.ok(id + " numaralı masanın hesabı kapatıldı. Yine bekleriz!");
    }
}
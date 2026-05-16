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
        return ResponseEntity.ok(id + " numaralı masa başarıyla açıldı!");
    }

    @PostMapping("/tables/{id}/add-order")
    public ResponseEntity<String> addOrder(
            @PathVariable Long id,
            @RequestParam Long menuItemId,
            @RequestParam Integer quantity) {
        orderService.addOrderToTable(id, menuItemId, quantity);
        return ResponseEntity.ok("Sipariş masaya eklendi!");
    }

    @GetMapping("/tables/{id}/total")
    public ResponseEntity<String> getTableTotal(@PathVariable Long id) {
        Double total = orderService.calculateTotal(id);
        return ResponseEntity.ok(id + " numaralı masanın güncel hesap toplamı: " + total + " TL");
    }

    @PostMapping("/tables/{id}/checkout")
    public ResponseEntity<String> checkoutTable(@PathVariable Long id) {
        Double total = orderService.calculateTotal(id);
        orderService.checkoutTable(id);
        return ResponseEntity.ok(id + " numaralı masanın hesabı kapatıldı. Toplam Tahsil Edilen: " + total + " TL. Yine bekleriz!");
    }
}
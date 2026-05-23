package com.kahvekosesi.controller;

import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.kahvekosesi.dto.MenuItemDto;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final OrderService orderService;

    @GetMapping("/")
    public String showCustomerMenu(Model model) {
        model.addAttribute("menu", orderService.getAllMenuItems());
        model.addAttribute("tables", orderService.getAllTables());
        return "index";
    }

    @PostMapping("/customer/order")
    public String customerAddOrder(@RequestParam Long tableId, @RequestParam Long menuItemId, @RequestParam Integer quantity) {
        orderService.addOrderToTable(tableId, menuItemId, quantity);
        return "redirect:/?success=true";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "patron".equals(password)) {
            return "redirect:/admin";
        } else if ("garson".equals(username) && "123".equals(password)) {
            return "redirect:/waiter";
        } else {
            model.addAttribute("error", true);
            return "login";
        }
    }
    @GetMapping("/waiter")
    public String showWaiterPanel(Model model) {
        model.addAttribute("tables", orderService.getAllTables());
        return "waiter";
    }

    @PostMapping("/waiter/tables/{id}/checkout")
    public String waiterCheckoutTable(@PathVariable Long id) {
        orderService.checkoutTable(id);
        return "redirect:/waiter";
    }

    @GetMapping("/admin")
    public String showAdminPanel(Model model) {
        model.addAttribute("menu", orderService.getAllMenuItems());
        model.addAttribute("dailySales", orderService.getDailyTotalSales());
        return "admin";
    }

    @PostMapping("/admin/add-product")
    public String adminAddProduct(@Valid @ModelAttribute MenuItemDto dto,
                                  BindingResult result) {

        if (result.hasErrors()) {
            return "admin";
        }

        MenuItem menuItem = new MenuItem();
        menuItem.setName(dto.getName());
        menuItem.setPrice(dto.getPrice());
        menuItem.setCategory(dto.getCategory());

        orderService.saveMenuItem(menuItem);

        return "redirect:/admin";
    }

    @PostMapping("/admin/update-price/{id}")
    public String adminUpdatePrice(@PathVariable Long id, @RequestParam Double newPrice) {
        orderService.updateProductPrice(id, newPrice);
        return "redirect:/admin";
    }
}
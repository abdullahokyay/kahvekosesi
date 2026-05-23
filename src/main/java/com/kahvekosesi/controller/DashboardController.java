package com.kahvekosesi.controller;

import com.kahvekosesi.dto.MenuItemDto;
import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final OrderService orderService;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/menu")
    public String showMenuPage(Model model) {
        model.addAttribute("menu", orderService.getAllMenuItems());
        model.addAttribute("tables", orderService.getAllTables());
        return "menu";
    }

    @PostMapping("/customer/order")
    public String customerAddOrder(@RequestParam Long tableId,
                                   @RequestParam Long menuItemId,
                                   @RequestParam Integer quantity) {
        orderService.addOrderToTable(tableId, menuItemId, quantity);
        return "redirect:/menu?success=true";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
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
        model.addAttribute("menuItemDto", new MenuItemDto());
        return "admin";
    }

    @PostMapping("/admin/add-product")
    public String adminAddProduct(@Valid @ModelAttribute("menuItemDto") MenuItemDto dto,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("menu", orderService.getAllMenuItems());
            model.addAttribute("dailySales", orderService.getDailyTotalSales());
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
    public String adminUpdatePrice(@PathVariable Long id,
                                   @RequestParam Double newPrice) {
        orderService.updateProductPrice(id, newPrice);
        return "redirect:/admin";
    }
}
package com.kahvekosesi.config;

import com.kahvekosesi.entity.MenuItem;
import com.kahvekosesi.entity.RestaurantTable;
import com.kahvekosesi.repository.MenuItemRepository;
import com.kahvekosesi.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantTableRepository restaurantTableRepository;

    @Override
    public void run(String... args) throws Exception {
        // Menüye Ürünleri Ekleme
        if (menuItemRepository.count() == 0) {

            //Sıcak Kahveler
            menuItemRepository.save(new MenuItem(null, "Espresso", 70.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Americano", 80.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Caffe Latte", 90.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Flat White", 95.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Cappuccino", 90.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Cortado", 85.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Filter Coffee", 75.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "White Chocolate Mocha", 110.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Caramel Macchiato", 105.0, "Sıcak Kahve"));
            menuItemRepository.save(new MenuItem(null, "Türk Kahvesi", 65.0, "Sıcak Kahve"));

            // Soğuk Kahveler
            menuItemRepository.save(new MenuItem(null, "Iced Americano", 85.0, "Soğuk Kahve"));
            menuItemRepository.save(new MenuItem(null, "Iced Latte", 95.0, "Soğuk Kahve"));
            menuItemRepository.save(new MenuItem(null, "Iced Caramel Macchiato", 110.0, "Soğuk Kahve"));
            menuItemRepository.save(new MenuItem(null, "Iced Mocha", 115.0, "Soğuk Kahve"));
            menuItemRepository.save(new MenuItem(null, "Cold Brew", 100.0, "Soğuk Kahve"));
            menuItemRepository.save(new MenuItem(null, "Frappe (Chocolate/Vanilla)", 120.0, "Soğuk Kahve"));

            // Tatlılar
            menuItemRepository.save(new MenuItem(null, "San Sebastian Cheesecake", 140.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Tiramisu (Orijinal Mascarpone)", 130.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Çilekli Magnolia", 115.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Mozaik Pasta", 95.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Fıstıklı Havuç Dilim Baklava", 160.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Profiterol", 110.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Fıstıklı Brownie", 125.0, "Tatlı"));
            menuItemRepository.save(new MenuItem(null, "Limonlu Tart", 105.0, "Tatlı"));

            // Alternatif İçecekler
            menuItemRepository.save(new MenuItem(null, "Türk Çayı (Duble)", 35.0, "İçecek"));
            menuItemRepository.save(new MenuItem(null, "Ev Yapımı Limonata", 85.0, "İçecek"));
            menuItemRepository.save(new MenuItem(null, "Taze Sıkılmış Portakal Suyu", 95.0, "İçecek"));
            menuItemRepository.save(new MenuItem(null, "Bitki Çayı (Yeşil Çay/Adaçayı)", 75.0, "İçecek"));
            menuItemRepository.save(new MenuItem(null, "Sıcak Çikolata", 90.0, "İçecek"));
            menuItemRepository.save(new MenuItem(null, "Churchill", 50.0, "İçecek"));

            System.out.println(">>> 30 ÇEŞİTLİK DEV KAFE MENÜSÜ VERİ TABANINA BAŞARIYLA YÜKLENDİ <<<");
        }

        //  Masaları Kurma
        if (restaurantTableRepository.count() == 0) {
            for (int i = 1; i <= 10; i++) {
                restaurantTableRepository.save(new RestaurantTable(null, i, false));
            }
            System.out.println(">>> 10 ADET KAFE MASASI VERİ TABANINDA OLUŞTURULDU <<<");
        }
    }
}
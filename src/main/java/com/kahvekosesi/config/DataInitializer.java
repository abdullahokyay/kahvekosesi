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

            menuItemRepository.save(new MenuItem(null,"Espresso",70.0,"Sıcak Kahve","/images/menu/espresso.jpg"));
            menuItemRepository.save(new MenuItem(null,"Americano",80.0,"Sıcak Kahve","/images/menu/americano.jpg"));
            menuItemRepository.save(new MenuItem(null,"Caffe Latte",90.0,"Sıcak Kahve","/images/menu/caffe-latte.jpg"));
            menuItemRepository.save(new MenuItem(null,"Flat White",95.0,"Sıcak Kahve","/images/menu/flat-white.jpg"));
            menuItemRepository.save(new MenuItem(null,"Cappuccino",90.0,"Sıcak Kahve","/images/menu/cappuccino.jpg"));
            menuItemRepository.save(new MenuItem(null,"Cortado",85.0,"Sıcak Kahve","/images/menu/cortado.jpg"));
            menuItemRepository.save(new MenuItem(null,"Filter Coffee",75.0,"Sıcak Kahve","/images/menu/filter-coffee.jpg"));
            menuItemRepository.save(new MenuItem(null,"White Chocolate Mocha",110.0,"Sıcak Kahve","/images/menu/white-chocolate-mocha.jpg"));
            menuItemRepository.save(new MenuItem(null,"Caramel Macchiato",105.0,"Sıcak Kahve","/images/menu/caramel-macchiato.jpg"));
            menuItemRepository.save(new MenuItem(null,"Türk Kahvesi",65.0,"Sıcak Kahve","/images/menu/turk-kahvesi.jpg"));

            menuItemRepository.save(new MenuItem(null,"Iced Americano",85.0,"Soğuk Kahve","/images/menu/iced-americano.jpg"));
            menuItemRepository.save(new MenuItem(null,"Iced Latte",95.0,"Soğuk Kahve","/images/menu/iced-latte.jpg"));
            menuItemRepository.save(new MenuItem(null,"Iced Caramel Macchiato",110.0,"Soğuk Kahve","/images/menu/iced-caramel-macchiato.jpg"));
            menuItemRepository.save(new MenuItem(null,"Iced Mocha",115.0,"Soğuk Kahve","/images/menu/iced-mocha.jpg"));
            menuItemRepository.save(new MenuItem(null,"Cold Brew",100.0,"Soğuk Kahve","/images/menu/cold-brew.jpg"));
            menuItemRepository.save(new MenuItem(null,"Frappe (Chocolate/Vanilla)",120.0,"Soğuk Kahve","/images/menu/frappe.jpg"));

            menuItemRepository.save(new MenuItem(null,"San Sebastian Cheesecake",140.0,"Tatlı","/images/menu/san-sebastian-cheesecake.jpg"));
            menuItemRepository.save(new MenuItem(null,"Tiramisu (Orijinal Mascarpone)",130.0,"Tatlı","/images/menu/tiramisu.jpg"));
            menuItemRepository.save(new MenuItem(null,"Çilekli Magnolia",115.0,"Tatlı","/images/menu/cilekli-magnolia.jpg"));
            menuItemRepository.save(new MenuItem(null,"Mozaik Pasta",95.0,"Tatlı","/images/menu/mozaik-pasta.jpg"));
            menuItemRepository.save(new MenuItem(null,"Fıstıklı Havuç Dilim Baklava",160.0,"Tatlı","/images/menu/fistikli-havuc-dilim-baklava.jpg"));
            menuItemRepository.save(new MenuItem(null,"Profiterol",110.0,"Tatlı","/images/menu/profiterol.jpg"));
            menuItemRepository.save(new MenuItem(null,"Fıstıklı Brownie",125.0,"Tatlı","/images/menu/fistikli-brownie.jpg"));
            menuItemRepository.save(new MenuItem(null,"Limonlu Tart",105.0,"Tatlı","/images/menu/limonlu-tart.jpg"));

            menuItemRepository.save(new MenuItem(null,"Türk Çayı (Duble)",35.0,"İçecek","/images/menu/turk-cayi.jpg"));
            menuItemRepository.save(new MenuItem(null,"Ev Yapımı Limonata",85.0,"İçecek","/images/menu/limonata.jpg"));
            menuItemRepository.save(new MenuItem(null,"Taze Sıkılmış Portakal Suyu",95.0,"İçecek","/images/menu/portakal-suyu.jpg"));
            menuItemRepository.save(new MenuItem(null,"Bitki Çayı (Yeşil Çay/Adaçayı)",75.0,"İçecek","/images/menu/bitki-cayi.jpg"));
            menuItemRepository.save(new MenuItem(null,"Sıcak Çikolata",90.0,"İçecek","/images/menu/sicak-cikolata.jpg"));
            menuItemRepository.save(new MenuItem(null,"Churchill",50.0,"İçecek","/images/menu/churchill.jpg"));

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
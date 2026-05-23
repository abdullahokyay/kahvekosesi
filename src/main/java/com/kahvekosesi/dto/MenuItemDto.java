package com.kahvekosesi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class MenuItemDto {

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @Positive(message = "Fiyat 0'dan büyük olmalıdır")
    private Double price;

    @NotBlank(message = "Kategori boş olamaz")
    private String category;
}
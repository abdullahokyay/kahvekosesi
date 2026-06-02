package com.kahvekosesi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MenuItemDto {

    @NotBlank(message = "Ürün adı boş olamaz")
    private String name;

    @Positive(message = "Fiyat 0'dan büyük olmalıdır")
    private Double price;

    @NotBlank(message = "Kategori boş olamaz")
    private String category;

    private MultipartFile imageFile;
}
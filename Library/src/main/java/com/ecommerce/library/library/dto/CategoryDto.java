package com.ecommerce.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    private Long categoryId;

    @Size(min=2,message = "Invalid name(Atleast 1 characters)")
    private String categoryName;
    private Long numberOfProduct;
}
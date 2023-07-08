package com.ecommerce.library.library.service;

import com.ecommerce.library.library.dto.CategoryDto;
import com.ecommerce.library.library.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    /*admin*/
    List<Category> findAll();
    String save(Category category);
    Category findById(Long id);
    Category update(Category category);
    void deletedById(Long id);
    void enableById(Long id);
     Category findByName(String name);
     List<Category> findAllByActivated();
    /*Customer*/
    List<CategoryDto> getCategoryAndProduct();

}

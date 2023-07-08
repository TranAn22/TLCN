package com.ecommerce.library.library.service.impl;

import com.ecommerce.library.library.dto.CategoryDto;
import com.ecommerce.library.library.model.Category;
import com.ecommerce.library.library.repository.CategoryRepository;
import com.ecommerce.library.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repo;
    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }

    @Override
    public Category findByName(String name){
        return repo.findByName(name);
    }

    @Override
    public List<Category> findAllByActivated() {
        return repo.findAllByActivated();
    }

    @Override
    public String save(Category category) {
            Category catecheck=(findByName(category.getName()));
            if(catecheck==null) {
                Category categorySave = new Category(category.getName());
                repo.save(categorySave);
                return "success";
            }
            return "fail";

    }

    @Override
    public Category findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate=null;
        try{
            categoryUpdate=repo.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.set_activated(true);
            categoryUpdate.set_deleted(false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return repo.save(categoryUpdate);
    }

    @Override
    public void deletedById(Long id) {
        Category category=repo.getById(id);
        category.set_deleted(true);
        category.set_activated(false);
        repo.save(category);
    }

    @Override
    public void enableById(Long id) {
        Category category=repo.getById(id);
        category.set_deleted(false);
        category.set_activated(true);
        repo.save(category);
    }
    @Override
    public List<CategoryDto> getCategoryAndProduct() {
        return repo.getCategoryAndProduct();
    }
}

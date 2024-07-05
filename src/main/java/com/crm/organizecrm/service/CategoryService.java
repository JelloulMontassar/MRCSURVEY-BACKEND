package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}

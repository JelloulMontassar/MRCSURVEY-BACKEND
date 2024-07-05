package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.exception.CategoryNotFoundException;
import com.crm.organizecrm.model.Category;
import com.crm.organizecrm.repository.CategoryRepository;
import com.crm.organizecrm.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        existingCategory.setType(category.getType());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

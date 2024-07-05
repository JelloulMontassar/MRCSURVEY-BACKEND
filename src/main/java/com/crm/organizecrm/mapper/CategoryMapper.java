package com.crm.organizecrm.mapper;

import com.crm.organizecrm.dto.CategoryDTO;
import com.crm.organizecrm.model.Category;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .type(category.getType())
                .build();
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .type(categoryDTO.getType())
                .build();
    }
}

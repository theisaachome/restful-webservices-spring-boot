package com.isaachome.blog.service;

import com.isaachome.blog.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto dto);
    CategoryDto getCategoryById(long categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto updateCategory(CategoryDto dto,long id);
    void deleteCategory(long id);
}

package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Category;
import com.example.test_restful.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() { return categoryRepository.findAll(); }
    public Optional<Category> getCategoryById(Long id) { return categoryRepository.findById(id); }

    @Transactional
    public Category createCategory(Category category) { return categoryRepository.save(category); }

    public Category updateCategory(Category category) { return categoryRepository.save(category); }

    public void deleteCategory(Long id) { categoryRepository.deleteById(id); }
}
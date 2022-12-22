package com.ss7.service.category.impl;

import com.ss7.model.Category;
import com.ss7.repository.ICategoryRepository;
import com.ss7.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category categoryNew) {
        categoryRepository.save(categoryNew);
    }

    @Override
    public void deleteBlog(Integer id) {
        categoryRepository.deleteById(id);
    }
}

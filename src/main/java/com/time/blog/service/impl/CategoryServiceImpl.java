package com.time.blog.service.impl;

import com.time.blog.domain.dto.CategoryDTO;
import com.time.blog.domain.entity.Article;
import com.time.blog.domain.entity.Category;
import com.time.blog.mapper.CategoryMapper;
import com.time.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mjw
 * @date 2023/4/25
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public List<CategoryDTO> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public List<Article> getByCateType(String categoryId) {
        return categoryMapper.getByCateType(categoryId);
    }

    @Override
    public Long getCateIdByName(String categoryName) {
        return categoryMapper.getCateIdByName(categoryName);
    }
}

package com.time.blog.mapper;

import com.time.blog.domain.dto.CategoryDTO;
import com.time.blog.domain.entity.Article;
import com.time.blog.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mjw
 * @date 2023/4/25
 */
@Mapper
public interface CategoryMapper {

    /**
     * 获取所有的分类信息
     * @return 分类信息list
     */
    List<Category> getAllCategory();

    /**
     * 获取所有的文章分类
     * @return 文章分类list
     */
    List<CategoryDTO> getCategoryList();

    /**
     * 根据文章类型查询
     * @param categoryId categoryId
     * @return list
     */
    List<Article> getByCateType(String categoryId);

    /**
     * 通过categoryName获取categoryId
     * @param categoryName categoryName
     * @return categoryId
     */
    Long getCateIdByName(String categoryName);
}

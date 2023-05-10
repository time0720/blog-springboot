package com.time.blog.controller;

import com.time.blog.aop.OperationLogAnnotation;
import com.time.blog.domain.dto.CategoryDTO;
import com.time.blog.domain.entity.Article;
import com.time.blog.domain.entity.Category;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mjw
 * @date 2023/4/25
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @OperationLogAnnotation(operModelCode = "CATEGORY", operType = "保存", operDesc = "获取所有的分类信息")
    @ApiOperation("获取所有的分类信息")
    @GetMapping("/getAllCategory")
    public ResponseResult<List<Category>> getAllCategory() {
        return ResponseResult.success(categoryService.getAllCategory());
    }

    @OperationLogAnnotation(operModelCode = "CATEGORY", operType = "保存", operDesc = "获取所有的文章分类")
    @ApiOperation("获取所有的文章分类")
    @GetMapping("/getCategoryList")
    public ResponseResult<List<CategoryDTO>> getCategoryList() {
        return ResponseResult.success(categoryService.getCategoryList());
    }

    @OperationLogAnnotation(operModelCode = "CATEGORY", operType = "保存", operDesc = "根据文章类型查询")
    @ApiOperation("根据文章类型查询")
    @GetMapping("/getByCateType/{categoryId}")
    public ResponseResult<List<Article>> getByCateType(@PathVariable("categoryId") String categoryId) {
        return ResponseResult.success(categoryService.getByCateType(categoryId));
    }

}

package com.time.blog.controller;

import com.github.pagehelper.PageInfo;
import com.time.blog.aop.OperationLogAnnotation;
import com.time.blog.domain.entity.Article;
import com.time.blog.reslut.ResponseResult;
import com.time.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mjw
 * @date 2023/4/13
 */
@RestController
@Api(tags = "文章管理")
@Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @OperationLogAnnotation(operModelCode = "ARTICLE", operType = "查询", operDesc = "查询文章")
    @ApiOperation("查询文章")
    @GetMapping("/article/selectArticle")
    public ResponseResult<PageInfo<Article>> selectArticle(int pageNum, int pageSize, Article article) {
        return ResponseResult.success(articleService.selectArticleByCondition(pageNum, pageSize, article));
    }

    @ApiOperation("所有文章的列表")
    @GetMapping("/article/selectAll")
    public ResponseResult<List<Article>> selectAll() {
        return ResponseResult.success(articleService.selectAll());
    }

    @OperationLogAnnotation(operModelCode = "ARTICLE", operType = "查询", operDesc = "查询文章详情")
    @ApiOperation("查询文章详情")
    @GetMapping("/article/detail/{articleId}")
    public ResponseResult<Article> detail(@PathVariable("articleId") Long articleId) {
        return ResponseResult.success(articleService.detail(articleId));
    }

    @OperationLogAnnotation(operModelCode = "ARTICLE", operType = "删除", operDesc = "删除文章")
    @ApiOperation("删除文章-逻辑删除")
    @PostMapping("/admin/deleteArticles")
    public ResponseResult<Void> deleteArticles(@RequestBody List<Long> articleIds) {
        articleService.deleteArticles(articleIds);
        return ResponseResult.success();
    }

    @OperationLogAnnotation(operModelCode = "ARTICLE", operType = "保存", operDesc = "保存文章")
    @ApiOperation("保存文章")
    @PostMapping("/admin/saveArticle")
    public ResponseResult<Void> saveArticle(@RequestBody Article article) {
        articleService.saveArticle(article);
        return ResponseResult.success();
    }

    @OperationLogAnnotation(operModelCode = "ARTICLE", operType = "查询", operDesc = "使用ES搜索，关键词：")
    @ApiOperation("使用ES搜索文章")
    @GetMapping("/article/findByEs")
    public ResponseResult<List<Article>> findByEs(String keyword) {
        return ResponseResult.success(articleService.findByEs(keyword));
    }
}

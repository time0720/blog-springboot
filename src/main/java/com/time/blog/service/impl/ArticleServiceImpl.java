package com.time.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.Article;
import com.time.blog.mapper.ArticleMapper;
import com.time.blog.mapper.CategoryMapper;
import com.time.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author mjw
 * @date 2023/4/13
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final CategoryMapper categoryMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, CategoryMapper categoryMapper) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public PageInfo<Article> selectArticleByCondition(int pageNum, int pageSize, Article article) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articleList = articleMapper.selectArticle(article);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override
    public List<Article> selectAll() {
        return articleMapper.selectAll();
    }

    @Override
    public Article detail(Long articleId) {
        return articleMapper.detail(articleId);
    }

    @Override
    public void deleteArticles(List<Long> articleIds) {
        articleMapper.deleteArticles(articleIds);
    }

    @Override
    public void saveArticle(Article article) {
        if (Objects.isNull(article)) {
            return;
        }
        //处理category
        String categoryName = article.getCategoryName();
        Long categoryId = categoryMapper.getCateIdByName(categoryName);
        if (article.getArticleId() == null) {
            // TODO：完善categoryId和userId
            article.setCategoryId(categoryId);
            article.setUserId(1L);
            article.setDeleteFlag("N");
            article.setCreationDate(new Date());
            article.setLastUpdateTime(new Date());
            articleMapper.insertArticle(article);
        } else {
            article.setCategoryId(categoryId);
            article.setLastUpdateTime(new Date());
            articleMapper.updateArticle(article);
        }
    }
}

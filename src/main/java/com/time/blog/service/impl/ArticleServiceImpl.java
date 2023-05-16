package com.time.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.Article;
import com.time.blog.mapper.ArticleMapper;
import com.time.blog.mapper.CategoryMapper;
import com.time.blog.repository.EsArticleRepository;
import com.time.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final EsArticleRepository esArticleRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, CategoryMapper categoryMapper, EsArticleRepository esArticleRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.articleMapper = articleMapper;
        this.categoryMapper = categoryMapper;
        this.esArticleRepository = esArticleRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
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
        article.setCategoryId(categoryId);
        if (article.getArticleId() == null) {
            // TODO：完善categoryId和userId
            article.setUserId(1L);
            article.setDeleteFlag("N");
            article.setCreationDate(new Date());
            article.setLastUpdateTime(new Date());
            articleMapper.insertArticle(article);
        } else {
            article.setLastUpdateTime(new Date());
            articleMapper.updateArticle(article);
        }
        Long articleId = article.getArticleId();
        log.info("当前文章的ID为--->{}", articleId);
        // 写入ES
        try {
            log.info("要存入ES的数据为--->{}", article);
            esArticleRepository.save(article);
        } catch (Exception e) {
            log.error("存入ES失败--->{}", ExceptionUtils.getMessage(e));
        }

    }

    @Override
    public List<Article> findByEs(String keyword) {
        NativeSearchQueryBuilder query = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(keyword)) {
            // 根据文章标题和内容当作关键字查询
            boolQueryBuilder.must(QueryBuilders.matchQuery("articleTitle", keyword));
            boolQueryBuilder.should(QueryBuilders.matchQuery("articleContent", keyword));
            // 不能查询出已经被删除的文章
            boolQueryBuilder.mustNot(QueryBuilders.matchQuery("deleteFlag", "Y"));
        }
        query.withQuery(boolQueryBuilder);
        SearchHits<Article> searchHits = elasticsearchRestTemplate.search(query.build(), Article.class);
        List<Article> list = new ArrayList<>();
        for (SearchHit<Article> searchHit : searchHits) {
            list.add(searchHit.getContent());
        }
        return list;
    }
}

package com.time.blog.service;

import com.github.pagehelper.PageInfo;
import com.time.blog.domain.entity.Article;
import org.springframework.data.elasticsearch.core.SearchHits;

import java.util.List;

/**
 * @author mjw
 * @date 2023/4/13
 */
public interface ArticleService {

    /**
     * 根据条件查询文章信息
     * @param pageNum pageNum
     * @param pageSize pageSize
     * @param article article
     * @return article
     */
    PageInfo<Article> selectArticleByCondition(int pageNum, int pageSize, Article article);

    /**
     * 所有文章的列表
     * @return all
     */
    List<Article> selectAll();

    /**
     * 查询文章详情
     * @param articleId 文章id
     * @return 文章详情
     */
    Article detail(Long articleId);

    /**
     * 删除文章-逻辑删除
     * @param articleIds 文章ids
     */
    void deleteArticles(List<Long> articleIds);

    /**
     * 保存文章
     * @param article article
     * @return article
     */
    void saveArticle(Article article);

    /**
     * 使用ES，根据文章标题和内容搜索
     * @param keyword 关键词
     * @return article
     */
    List<Article> findByEs(String keyword);
}

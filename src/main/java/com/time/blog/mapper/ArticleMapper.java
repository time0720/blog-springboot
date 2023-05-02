package com.time.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.time.blog.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 根据条件查询文章信息
     * @param article article
     * @return article
     */
    List<Article> selectArticle(Article article);

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
     * 新增文章
     * @param article article
     */
    void insertArticle(Article article);

    /**
     * 修改文章
     * @param article article
     */
    void updateArticle(Article article);
}
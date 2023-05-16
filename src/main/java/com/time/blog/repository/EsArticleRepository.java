package com.time.blog.repository;

import com.time.blog.domain.entity.Article;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author mjw
 * @date 2023/5/16
 */
public interface EsArticleRepository extends ElasticsearchRepository<Article, String> {

//    @Highlight(fields = {
//            @HighlightField(name = "articleTitle"),
//            @HighlightField(name = "articleContent")
//    })
//    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"articleTitle\",\"articleContent\"]}}")
//    SearchHits<Article> findByEs(String keyword);

}

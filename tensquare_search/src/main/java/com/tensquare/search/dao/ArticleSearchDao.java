package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author jinjingfei
 * @date 2019/9/17 22:42
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    Page<Article> findByTitleLike(String keywords,Pageable pageable);
}

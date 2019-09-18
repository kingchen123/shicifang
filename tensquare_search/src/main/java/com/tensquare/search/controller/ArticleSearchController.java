package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinjingfei
 * @date 2019/9/17 22:45
 */
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService searchService;

    /**
     * 增
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        searchService.add(article);
        return new Result(true, StatusCode.OK,"文章添加成功" );

    }

    @RequestMapping(value = "/search/{keywords}/{pageNumber}/{pageSize}",method = RequestMethod.GET)
    public Result search(@PathVariable String keywords,@PathVariable Integer pageNumber,@PathVariable Integer pageSize){
       Page<Article> articleList =  searchService.findByTitleOrContent(keywords,pageNumber,pageSize);
        return new Result(true,StatusCode.OK ,"查询成功" ,new PageResult<>(articleList.getTotalElements(),articleList.getContent()));

    }

}

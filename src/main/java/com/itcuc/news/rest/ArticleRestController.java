package com.itcuc.news.rest;

import com.itcuc.common.Result;
import com.itcuc.common.ResultCode;
import com.itcuc.news.entity.Article;
import com.itcuc.news.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
@Api(value = "新闻接口")
public class ArticleRestController {

    @Autowired
    ArticleService articleService;

    @PostMapping("articles")
    public Result articles(String title,String content) {
        return null;
    }

    @GetMapping("articles")
    @ApiOperation(value = "查询新闻列表")
    public Result articles() {
        List<Article> list = articleService.list();
        if(list != null) {
            return Result.success(list);
        } else {
            return Result.failure(ResultCode.DATA_IS_WRONG);
        }
    }

    @DeleteMapping("articles")
    @ApiOperation("删除一条新闻")
    @ApiImplicitParam(value = "id",required = true)
    public Result delete(@PathVariable String id) {
        String ret = articleService.delete(id);
        if(StringUtils.isNotBlank(ret)) {
            return Result.success(ret);
        } else {
            return Result.failure(ResultCode.RESULE_DATA_NONE);
        }
    }
}

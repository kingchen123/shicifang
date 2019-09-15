package com.pingan.spit.controller;

import com.pingan.spit.pojo.Spit;
import com.pingan.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 增
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result dele(@PathVariable String spitId) {
        spitService.dele(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 改
     *
     * @param spit
     * @param spitId
     * @return
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String spitId) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 查一个
     */
    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result findByOne(@PathVariable String spitId) {
        spitService.findByOne(spitId);
        return new Result(true, StatusCode.OK, "查询成功");
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        spitService.findAll();
        return new Result(true, StatusCode.OK, "查询成功");
    }

    /**
     * 根据上级ID查询吐槽列表
     */
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK, "根据上级ID查询吐槽列表成功", new PageResult<>(spitPage.getTotalElements(), spitPage.getContent()));
    }

    /**
     * 吐槽点赞
     *
     * @param id
     * @return
     */
  /*  @RequestMapping(value = "/thumbup/{id}", method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id) {
        //判断用户是否点过赞
        //key: 业务唯一标识+用户唯一id+吐槽唯一id
        String userId = "1";// 后续等加入jwt就有userId
        String isThumbup = (String) redisTemplate.opsForValue().get("thumbup_" + userId + "_" + id);
        if (!StringUtils.isEmpty(isThumbup)) {
            return new Result(false, StatusCode.ERROR, "已经点赞无需重复操作");
        }
        spitService.updateThumbup(id);
        //点赞后 将点赞记录存入redis
        redisTemplate.opsForValue().set("thumbup_" + userId + "_" + id, "1");
        return new Result(true, StatusCode.OK, "点赞成功");
    }*/

    /**
     * 吐槽点赞
     * @return
     */
    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
  public Result updateThumbup(@PathVariable String spitId){
        //判断用户是否点过赞
        //key: 业务唯一标识+用户唯一id+吐槽唯一id
        String userId = "1";// 后续等加入jwt就有userId
        String isThumbup = (String) redisTemplate.opsForValue().get("thumbup_"+userId+"_"+spitId);//获取点赞value值(点赞数)
        if (!StringUtils.isEmpty(isThumbup)){//已经点赞
            return new Result(false, StatusCode.ERROR, "已经点赞无需重复操作");
        }
        //没有点赞
        spitService.updateThumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_" + userId + "_" + spitId, "1");
        return new Result(true,StatusCode.OK ,"吐槽点赞成功" );

  }
}

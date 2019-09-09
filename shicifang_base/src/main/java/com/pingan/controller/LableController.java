package com.pingan.controller;

import com.pingan.pojo.Lable;
import com.pingan.service.LableService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@CrossOrigin
public class LableController {

    @Autowired
    private LableService lableService;

    /**
     * 增
     * @param lable
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Lable lable){
       lableService.add(lable);
       return new Result(true, StatusCode.OK,"增加成功" );
    }

    /**
     * 删除
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId){
        lableService.deleteById(labelId);
        return new Result(true,StatusCode.OK ,"删除成功" );
    }

    /**
     * 改
     * @param labelId
     * @param lable
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId,@RequestBody Lable lable){
        lable.setId(labelId);
        lableService.update(lable);
        return new Result(true,StatusCode.OK ,"修改成功" );
    }

    /**
     * 查
     * @param labelId
     * @return
     */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result select(@PathVariable String labelId){
       lableService.selectById(labelId);
        return new Result(true,StatusCode.OK ,"查询成功" );
    }

    /**
     * 根据条件查询
     * @param lable
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result selectByTerm(@RequestBody Lable lable){
        List<Lable> labelList = lableService.selectByTerm(lable);
        return new Result(true,StatusCode.OK ,"按条件查询成功",labelList );
    }

    /**
     * 根据条件分页查询
     * @param lable
     * @return
     */
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findByTerm(@RequestBody Lable lable,
                               @PathVariable int page,
                               @PathVariable int size){
       Page pageList = lableService.findByTerm(lable,page,size);
        return new Result(true,StatusCode.OK ,"按条件分页查询成功",new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }



}

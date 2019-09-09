package com.pingan.controller;

import com.pingan.pojo.Lable;
import com.pingan.service.LableService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lable")
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


}

package com.leduoduo.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leduoduo.health.constant.MessageConstant;
import com.leduoduo.health.exception.HealthException;
import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.entity.Result;
import com.leduoduo.health.pojo.CheckItem;
import com.leduoduo.health.service.CheckItemService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/1 0001
 * @Description:health_parent
 * @Version:1.0
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> list = checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        checkItemService.add(checkItem);
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);

    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        // 调用业务来分页，获取分页结果
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);

        //return pageResult;
        // 返回给页面, 包装到Result, 统一风格
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);

    }

   @GetMapping("/findById")
   public Result findById(int id){
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);

   }

    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
        checkItemService.update(checkItem);
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);

    }

    @PostMapping("/deleteById")
    public Result deleteById(int id){
        try {
            checkItemService.deleteById(id);
        } catch (HealthException e) {
            e.printStackTrace();
        }catch (RuntimeException e) {
            e.printStackTrace();
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

}

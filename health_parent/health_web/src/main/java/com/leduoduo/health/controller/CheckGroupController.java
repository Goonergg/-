package com.leduoduo.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leduoduo.health.constant.MessageConstant;
import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.entity.Result;
import com.leduoduo.health.exception.HealthException;
import com.leduoduo.health.pojo.CheckGroup;
import com.leduoduo.health.service.CheckGroupService;

import jdk.nashorn.internal.codegen.SpillObjectCreator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/3 0003
 * @Description:health_parent
 * @Version:1.0
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.add(checkGroup,checkitemIds);
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
            PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
    }

    @GetMapping("/findById")
    public Result findById(int id){
        CheckGroup checkGroup = checkGroupService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        List<Integer> checkItemIds = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemIds);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        checkGroupService.update(checkGroup,checkitemIds);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id) {
        try {
            checkGroupService.deleteById(id);

        } catch (HealthException e) {
            e.printStackTrace();
        }

        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> list = checkGroupService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);

    }
}

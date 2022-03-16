package com.leduoduo.health.service;

import com.leduoduo.health.exception.HealthException;
import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.pojo.CheckItem;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/1 0001
 * @Description:health_parent
 * @Version:1.0
 */
public interface CheckItemService {
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    CheckItem findById(int id);

    void update(CheckItem checkItem);

    void deleteById(int id) throws HealthException;



}

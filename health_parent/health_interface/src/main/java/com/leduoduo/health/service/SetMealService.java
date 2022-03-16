package com.leduoduo.health.service;

import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.pojo.CheckGroup;
import com.leduoduo.health.pojo.Setmeal;
import com.sun.xml.internal.ws.handler.HandlerException;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/5 0005
 * @Description:health_parent
 * @Version:1.0
 */
public interface SetMealService {
    void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult<Setmeal> findpage(QueryPageBean queryPageBean);

    Setmeal findById(int id);

    List<Integer> findCheckGroupIdsBySetMealId(int id);

    void update(Setmeal setmeal, Integer[] checkgroupIds);

    void deleteById(int id) throws HandlerException;
}

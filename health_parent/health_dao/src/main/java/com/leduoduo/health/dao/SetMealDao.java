package com.leduoduo.health.dao;

import com.github.pagehelper.Page;
import com.leduoduo.health.pojo.CheckGroup;
import com.leduoduo.health.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/5 0005
 * @Description:health_parent
 * @Version:1.0
 */
public interface SetMealDao {
    void add(Setmeal setmeal);


    void addSetMealCheckGroup(@Param("setmealId") Integer setmealId,@Param("checkgroupId") Integer checkgroupId);

    Page<Setmeal> findByCondition(String queryString);

    Setmeal findById(int id);

    List<Integer> findCheckGroupIdsBySetMealId(int id);

    void update(Setmeal setmeal);

    void deleteSetMealCheckGroup(Integer setmealId);

    int findOrderCountBySetmealId(int id);

    void deleteById(int id);
}

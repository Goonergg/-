package com.leduoduo.health.dao;

import com.github.pagehelper.Page;
import com.leduoduo.health.pojo.CheckItem;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/1 0001
 * @Description:health_parent
 * @Version:1.0
 */
public interface CheckItemDao {
    List<CheckItem> findAll();

    void add(CheckItem checkItem);

    Page<CheckItem> findByCondition(String queryString);

    CheckItem findById(int id);

    void update(CheckItem checkItem);

    int findCountByCheckItemId(int id);

    void deleteById(int id);
}

package com.leduoduo.health.service;


import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.exception.HealthException;
import com.leduoduo.health.pojo.CheckGroup;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/11/22
 */
public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过id查询选中的检查项id集合
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 编辑检查组更新
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 通过id删除检查组
     * @param id
     */
    void deleteById(int id) throws HealthException;

    List<CheckGroup> findAll();

}

package com.leduoduo.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.leduoduo.health.dao.SetMealDao;
import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.pojo.CheckGroup;
import com.leduoduo.health.pojo.Setmeal;
import com.leduoduo.health.service.SetMealService;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/5 0005
 * @Description:health_parent
 * @Version:1.0
 */
@Service(interfaceClass =SetMealService.class )
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;

    @Override
    @Transactional
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        if (null != checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setMealDao.addSetMealCheckGroup(setmealId,checkgroupId);
            }
        }
    }

    @Override
    public PageResult<Setmeal> findpage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
           queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<Setmeal> page = setMealDao.findByCondition(queryPageBean.getQueryString());

        return new PageResult<Setmeal>(page.getTotal(),page.getResult());
    }

    @Override
    public Setmeal findById(int id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckGroupIdsBySetMealId(int id) {

        return setMealDao.findCheckGroupIdsBySetMealId(id);

    }

    @Override
    public void update(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealDao.update(setmeal);
        Integer setmealId = setmeal.getId();
        setMealDao.deleteSetMealCheckGroup(setmealId);
        if (null != checkgroupIds){
            for (Integer checkgroupId : checkgroupIds) {
                setMealDao.addSetMealCheckGroup(setmealId,checkgroupId);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(int id)throws HandlerException {
        int count = setMealDao.findOrderCountBySetmealId(id);
        if (count > 0){
            throw new HandlerException("有订单正在使用此套餐，不能删除");
        }
        setMealDao.deleteSetMealCheckGroup(id);
        setMealDao.deleteById(id);
    }



}

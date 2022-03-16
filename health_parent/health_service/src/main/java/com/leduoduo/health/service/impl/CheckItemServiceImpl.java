package com.leduoduo.health.service.impl;



import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leduoduo.health.exception.HealthException;
import com.leduoduo.health.dao.CheckItemDao;

import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.pojo.CheckItem;
import com.leduoduo.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/3/1 0001
 * @Description:health_parent
 * @Version:1.0
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // 使用分页插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // 是否有查询条件 【注意】！不要漏掉了
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        // 调用dao查询
        Page<CheckItem> checkItemPage = checkItemDao.findByCondition(queryPageBean.getQueryString());
        long total = checkItemPage.getTotal();// 总计录数
        List<CheckItem> rows = checkItemPage.getResult();// 分页结果集
        return new PageResult<CheckItem>(total,rows);
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);

    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    @Override
    public void deleteById(int id) throws HealthException {
        //先判断这个检查项是否被检查组使用了
        //调用dao查询检查项的id是否在t_checkgroup_checkitem表中存在记录
        int cnt = checkItemDao.findCountByCheckItemId(id);
        //被使用了则不能删除
        if(cnt > 0){
            // 已经被检查组使用了，则不能删除，报错
            //??? health_web能捕获到这个异常吗？
            throw new HealthException("该检查项被检查组使用，不能删除");
        }
        //没使用就可以调用dao删除
        checkItemDao.deleteById(id);

    }


}

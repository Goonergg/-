package com.leduoduo.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leduoduo.health.exception.HealthException;
import com.leduoduo.health.dao.CheckGroupDao;
import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.pojo.CheckGroup;
import com.leduoduo.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2020/11/22
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 先添加检查组
        checkGroupDao.add(checkGroup);
        // 获取检查组的id
        Integer checkGroupId = checkGroup.getId();
        // 遍历选中的检查项ids数组
        if(null != checkitemIds){
            // 添加检查组与检查项的关系
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
    }

    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<CheckGroup> checkGroupPage = checkGroupDao.findByCondition(queryPageBean.getQueryString());

        return new PageResult<CheckGroup>(checkGroupPage.getTotal(),checkGroupPage.getResult());
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 通过id查询选中的检查项id集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 更新检查组
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.update(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        checkGroupDao.deleteCheckGroupCheckItem(checkGroupId);
        if (null != checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
    }

    /**
     * 通过id删除检查组
     * @param id
     * @throws HealthException
     */
    @Override
    @Transactional
    public void deleteById(int id) throws HealthException {
        // 判断是否被套餐使用
        int count = checkGroupDao.findCountByCheckGroupId(id);
        // 被使用了,报错
        if(count > 0){
            throw new HealthException("该检查组被套餐使用了，不能删除");
        }
        // 没使用
        //  先删除关系
        checkGroupDao.deleteCheckGroupCheckItem(id);
        //  再删除检查组
        checkGroupDao.deleteById(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }
}

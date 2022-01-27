package com.itheima.service;

import com.itheima.pojo.Items;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/1/27 0027
 * @Description:ssm_parent
 * @Version:1.0
 */
@Service
public interface ItemsService {

    public List<Items> findAll();



}

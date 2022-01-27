package com.itheima.controller;

import com.itheima.pojo.Items;
import com.itheima.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author:GG
 * @Date:2022/1/27 0027
 * @Description:ssm_parent
 * @Version:1.0
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    private ItemsService itemsService;
    @RequestMapping("/list")

    public String list(Model model){
        List<Items> list = itemsService.findAll();
        model.addAttribute("items",list);

        return "items";
    }


}

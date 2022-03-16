package com.leduoduo.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.leduoduo.health.Utils.QiNiuUtils;
import com.leduoduo.health.constant.MessageConstant;
import com.leduoduo.health.entity.PageResult;
import com.leduoduo.health.entity.QueryPageBean;
import com.leduoduo.health.entity.Result;
import com.leduoduo.health.pojo.CheckGroup;
import com.leduoduo.health.pojo.Setmeal;
import com.leduoduo.health.service.SetMealService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:GG
 * @Date:2022/3/5 0005
 * @Description:health_parent
 * @Version:1.0
 */
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    private SetMealService setMealService;

    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        setMealService.add(setmeal,checkgroupIds);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile){
        //- 获取原有图片名称，截取到后缀名
        String originalFilename = imgFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //- 生成唯一文件名，拼接后缀名
        String filename = UUID.randomUUID() + extension;
        //- 调用七牛上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(), filename);
            //- 返回数据给页面
            //{
            //    flag:
            //    message:
            //    data:{
            //        imgName: 图片名,
            //        domain: QiNiuUtils.DOMAIN
            //    }
            //}
            Map<String,String> map = new HashMap<String,String>();
            map.put("imgName",filename);
            map.put("domain", QiNiuUtils.DOMAIN);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    @PostMapping("/findpage")
    public Result findpage(@RequestBody QueryPageBean queryPageBean){
        PageResult<Setmeal> pageResult = setMealService.findpage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,pageResult);
    }

    @GetMapping("/findById")
    public Result findById(int id){
        Setmeal setmeal = setMealService.findById(id);
        // 前端要显示图片需要全路径
        // 封装到map中，解决图片路径问题
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("setmeal", setmeal); // formData
        resultMap.put("domain", QiNiuUtils.DOMAIN); // domain
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);


}
     @GetMapping("/findCheckGroupIdsBySetMealId")
     public Result findCheckGroupIdsBySetMealId(int id){
        List<Integer> checkgroupIds = setMealService.findCheckGroupIdsBySetMealId(id);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkgroupIds);

    }
    @PostMapping("/update")
    public Result update(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        setMealService.update(setmeal,checkgroupIds);
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @PostMapping("/deleteById")
    public Result deleteById(int id){
        setMealService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

}

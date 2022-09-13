package com.rh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Type;
import com.rh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/rh")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/category")
    public String category(){

        return "admin/category";
    }


    @PostMapping("/category/save")
    @ResponseBody
    public String types(Type type){

        typeService.saveType(type);

        return "成功";
    }
    
    @RequestMapping("/category/list")
    @ResponseBody
    public String categorylist(Integer page, Integer limit){

        System.out.println(page+":"+limit);
        PageHelper.startPage(page,limit);
        List<Type> types = typeService.findAllType();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        int r = (int) pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",types);
        map.put("totalCount",r);
        map.put("currPage",page);
        int sum = (int) Math.floor(r/limit)+1;
        map.put("totalPage",sum);

        return JSON.toJSONString(map);
    }


    @RequestMapping("/category/update")
    @ResponseBody
    public String categoryupdate(Type type){

        typeService.updateType(type);

        return "成功";
    }

    @RequestMapping("/category/delete")
    @ResponseBody
    public String categorydelete(Long id){

        typeService.deleteType(id);

        return "成功";
    }
}

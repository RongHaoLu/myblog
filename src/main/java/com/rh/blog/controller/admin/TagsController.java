package com.rh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Tag;
import com.rh.blog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rh")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @GetMapping("/tags")
    public String tags(){

        return "admin/tag";
    }

    @RequestMapping("/tags/list")
    @ResponseBody
    public String findAll(Integer page,Integer limit){

        PageHelper.startPage(page,limit);

        List<Tag> allTag = tagsService.findAllTag();

        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);

        int r = (int) pageInfo.getTotal();//得到条数
        System.out.println(r);

        Map<String,Object> map = new HashMap<>();

        map.put("list",allTag);
        map.put("totalCount",r);
        map.put("currPage",page);

        int sum = (int) Math.floor(r/limit)+1;

        map.put("totalPage",sum);


        return JSON.toJSONString(map);
    }

    @RequestMapping("/tags/save")
    @ResponseBody
    public String saveTag(Tag tag,String tag_name){

        System.out.println(tag+tag_name);

        tagsService.insertTag(tag);


        return "成功";
    }

    @RequestMapping("/tags/delete")
    @ResponseBody
    public String deleteTag(Long id){

        tagsService.deleteTag(id);


        return "成功";
    }

}

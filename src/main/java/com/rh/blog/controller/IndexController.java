package com.rh.blog.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Blog;
import com.rh.blog.pojo.Tag;
import com.rh.blog.pojo.Type;
import com.rh.blog.service.BlogService;
import com.rh.blog.service.TagsService;
import com.rh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagsService tagsService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView mv,Integer page){

        if(page==null){
            page=1;
        }

        PageHelper.startPage(page,8);
        List<Blog> blog = blogService.findAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blog);
        int total = (int) pageInfo.getTotal();
        int allpage = (int) (Math.floor(total / 8) + 1);
        mv.addObject("totalPage",allpage);
        mv.addObject("page",page);
        int countBlog = blogService.countBlog();
        mv.addObject("countBlog",countBlog);
        mv.addObject("blog",blog);

        List<Type> types = typeService.findAllType();
        mv.addObject("types",types);

        List<Tag> tags = tagsService.findAllTag();
        mv.addObject("tags",tags);

        List<Blog> recommendBlog = blogService.recommendBlog();
        mv.addObject("recommendBlog",recommendBlog);


        mv.setViewName("index");

        return mv;
    }

    @RequestMapping("/blogIndex")
    @ResponseBody
    public String blogIndex(ModelAndView mv,Integer page){

        if(page==null){
            page=1;
        }

        PageHelper.startPage(page,8);
        List<Blog> blog = blogService.findAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blog);
        int total = (int) pageInfo.getTotal();
        int allpage = (int) (Math.floor(total / 8) + 1);

        Map<String,Object> map = new HashMap<>();

        map.put("totalPage",allpage);
        map.put("page",page);
        map.put("blog",blog);

        //将map格式化为json格式
        return JSON.toJSONString(map);
    }
    

    @GetMapping("/about")
    public String about(){

        return "about";
    }

}

package com.rh.blog.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Blog;
import com.rh.blog.pojo.Type;
import com.rh.blog.service.BlogService;
import com.rh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FontTypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/types")
    public ModelAndView FontType(ModelAndView mv,Integer page,Long type_id){

        List<Type> types = typeService.findAllType();


        if(page==null){
            page=1;
        }
        if(type_id==null){
            type_id=types.get(0).getId();
        }

        PageHelper.startPage(page,6);
        List<Blog> blogs = blogService.findTypesBlog(type_id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        int total = (int) pageInfo.getTotal();
        int allpage = (int) (Math.floor(total / 6) + 1);


        mv.addObject("types",types);
        mv.addObject("blogs",blogs);
        mv.addObject("page",page);
        mv.addObject("totalPage",allpage);
        mv.addObject("type_id",type_id);


        mv.setViewName("types");

        return mv;
    }

}

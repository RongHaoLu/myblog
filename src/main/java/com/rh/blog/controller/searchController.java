package com.rh.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Blog;
import com.rh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class searchController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/search")
    public ModelAndView search(String query,Integer page,ModelAndView mv){
        if (page==null){
            page=1;
        }
        PageHelper.startPage(page,8);

        List<Blog> blogs = blogService.likeBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        int total = (int) pageInfo.getTotal();
        int totalPage = (int) (Math.floor(total / 8) + 1);
        mv.addObject("totalPage",totalPage);
        mv.addObject("blogs",blogs);
        mv.addObject("page",page);
        mv.addObject("total",total);
        mv.addObject("query",query);
        mv.setViewName("search");

        return mv;
    }
}

package com.rh.blog.controller;

import com.rh.blog.pojo.Blog;
import com.rh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ArchiveController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/archives")
    public ModelAndView Archives(ModelAndView mv){

        List<Blog> blogs = blogService.findAllBlog();
        mv.addObject("blogs",blogs);
        mv.setViewName("archives");

        System.out.println("nh");

        return mv;
    }
}

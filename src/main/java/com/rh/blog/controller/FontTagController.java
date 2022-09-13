package com.rh.blog.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FontTagController {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/tags")
    public ModelAndView FontType(ModelAndView mv, Integer page, Long tag_id){

        List<Tag> tags = tagsService.findAllTag();


        if(page==null){
            page=1;
        }
        if(tag_id==null){
            tag_id=tags.get(0).getTag_id();
        }

        PageHelper.startPage(page,8);
        List<Blog> blogs = blogService.findTagsBlog(tag_id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        int total = (int) pageInfo.getTotal();
        int allpage = (int) (Math.floor(total / 8) + 1);


        mv.addObject("tags",tags);
        mv.addObject("blogs",blogs);
        mv.addObject("page",page);
        mv.addObject("totalPage",allpage);
        mv.addObject("type_id",tag_id);


        mv.setViewName("tags");

        return mv;
    }
}

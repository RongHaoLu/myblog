package com.rh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Blog;
import com.rh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rh")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blog")
    public String Blog(){
        return "admin/blog";
    }

    @RequestMapping("/blog/list")
    @ResponseBody
    public String blog_list(Integer page,Integer limit){

        System.out.println("6666");


        PageHelper.startPage(page,limit);
        List<Blog> blogList = blogService.findAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
        int total = (int) pageInfo.getTotal();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("list",blogList);
        hashMap.put("totalCount",total);
        hashMap.put("currPage",page);
        int sumpage = (int) (Math.floor(total / limit) + 1);
        hashMap.put("totalPage",sumpage);

        return JSON.toJSONString(hashMap);
    }

    @PostMapping("/blogs/update")
    @ResponseBody
    public String blogs_update(Blog blog){

        blogService.updateBlog(blog);

        return "yes";
    }

    @PostMapping("/blogs/delete")
    @ResponseBody
    public String blogs_delete(Long id){

        blogService.deleteBlog(id);

        return "success";
    }

    @RequestMapping("/search")
    @ResponseBody
    public  String search(Integer page,Integer limit,String query){

        System.out.println(query);

        PageHelper.startPage(page,limit);

        List<Blog> blogs = blogService.likeBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        Map<String,Object> map = new HashMap<>();
        int total = (int) pageInfo.getTotal();
        int totalPage = (int) (Math.floor(total / 10) + 1);
        map.put("totalPage",totalPage);
        map.put("list",blogs);
        map.put("currPage",page);
        map.put("totalPage",total);

        return JSON.toJSONString(map);

    }



}

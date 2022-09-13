package com.rh.blog.controller.admin;

import com.rh.blog.pojo.Blog;
import com.rh.blog.pojo.Tag;
import com.rh.blog.pojo.Type;
import com.rh.blog.service.BlogService;
import com.rh.blog.service.TagsService;
import com.rh.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/rh")
public class EditController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private TagsService tagsService;

    @RequestMapping("/edit")
    public String Edit(HttpServletRequest request){

        List<Type> types = typeService.findAllType();
        request.setAttribute("categories",types);
        List<Tag> tags = tagsService.findAllTag();
        request.setAttribute("tags",tags);

        return "admin/edit";
    }

    @RequestMapping("/blogs/save")
    @ResponseBody
    public String blog_save(Blog blog){

        blogService.insertBlog(blog);

        return "yes";
    }

    @RequestMapping("/blog/edit/{id}")
    public String blog_edit(@PathVariable Long id,HttpServletRequest request){

        Blog blog = blogService.findOne(id);

        List<Type> types = typeService.findAllType();
        request.setAttribute("categories",types);
        request.setAttribute("blog",blog);

        List<Tag> tags = tagsService.findAllTag();
        request.setAttribute("tags",tags);

        return "admin/edit";
    }


}

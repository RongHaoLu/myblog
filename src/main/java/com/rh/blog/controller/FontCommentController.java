package com.rh.blog.controller;

import com.rh.blog.pojo.Comment;
import com.rh.blog.service.BlogService;
import com.rh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FontCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blog/{id}")
    public ModelAndView findOneBlog(@PathVariable Long id, ModelAndView mv){

        mv.addObject("blog",blogService.getAndConvert(id));
        System.out.println(blogService.getAndConvert(id));
        List<Comment> comments = commentService.findBlogComment(id);
        mv.addObject("comments",comments);
        mv.setViewName("blog");
        System.out.println(comments);

        return mv;
    }

    @RequestMapping("/comments")
    @ResponseBody
    public String saveComments(Comment comment){

        if (comment.getNickname().equals("葫芦娃娃")||comment.getNickname().equals("葫芦爸爸")||comment.getNickname().equals("葫芦爷爷")
                ||comment.getNickname().equals("葫芦娃他爸")){
            return "本娃专属名称不可用";
        }

        System.out.println(comment+"6666");

        commentService.saveComment(comment);

        return "等待葫芦娃娃审核通过";
    }
}

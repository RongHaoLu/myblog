package com.rh.blog.controller.admin;

import com.rh.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/rh")
public class adminIndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/index")
    public ModelAndView indexPage(ModelAndView mv){

        int countBlog = blogService.countBlog();
        mv.addObject("blogCount",countBlog);
        int countComment = commentService.countComment();
        mv.addObject("commentCount",countComment);
        int countType = typeService.countType();
        mv.addObject("categoryCount",countType);
        int countTag = tagsService.countTag();
        mv.addObject("tagCount",countTag);

        int countMessage = messageService.coutMessage();
        mv.addObject("countMessage",countMessage);


        mv.setViewName("admin/index");

        return mv;
    }


}

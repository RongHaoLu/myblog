package com.rh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Comment;
import com.rh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rh")
public class commentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/comments")
    public String comment(){


        return "admin/comment";
    }

    @RequestMapping("/comments/list")
    @ResponseBody
    public String commentList(Integer page,Integer limit){

        PageHelper.startPage(page,limit);
        List<Comment> comments = commentService.selectAllComment();
        PageInfo<Comment> pageInfo = new  PageInfo<>(comments);
        int total = (int) pageInfo.getTotal();
        int totalPage = (int) (Math.floor(total / limit) + 1);
        Map<String,Object> map = new HashMap<>();
        map.put("currPage",page);
        map.put("totalCount",total);
        map.put("totalPage",totalPage);
        map.put("list",comments);
        
        return JSON.toJSONString(map);
    }

    @PostMapping("/comments/pass")
    @ResponseBody
    public String commentPass(@RequestBody List<Integer> list){

        System.out.println(list);
        commentService.comment_pass(list);

        return "ok";
    }

    @PostMapping("/comments/delete")
    @ResponseBody
    public String commentDelete(@RequestBody List<Integer> list){

        commentService.comment_delete(list);

        return "ok";
    }

    @PostMapping("/comments/reply")
    @ResponseBody
    public String commentsReply(Long id,String meComment){
        System.out.println(meComment);
        System.out.println(id);

        commentService.adminComment(id,meComment);

        return "ok";
    }

}

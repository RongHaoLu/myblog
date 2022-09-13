package com.rh.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Comment;
import com.rh.blog.pojo.Message;
import com.rh.blog.service.MessageService;
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
public class messageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/message")
    public String message(){

        return "admin/message";
    }

    @RequestMapping("/messages/list")
    @ResponseBody
    public String commentList(Integer page,Integer limit){

        PageHelper.startPage(page,limit);
        List<Message> massages = messageService.selectAllMessage();
        PageInfo<Message> pageInfo = new  PageInfo<>(massages);
        int total = (int) pageInfo.getTotal();
        int totalPage = (int) (Math.floor(total / limit) + 1);
        Map<String,Object> map = new HashMap<>();
        map.put("currPage",page);
        map.put("totalCount",total);
        map.put("totalPage",totalPage);
        map.put("list",massages);
        System.out.println(massages);

        return JSON.toJSONString(map);
    }

    @PostMapping("/message/pass")
    @ResponseBody
    public String commentPass(@RequestBody List<Integer> list){

        System.out.println(list);
        messageService.message_pass(list);

        return "ok";
    }

    @PostMapping("/message/delete")
    @ResponseBody
    public String commentDelete(@RequestBody List<Integer> list){

        messageService.message_delete(list);

        return "ok";
    }

    @PostMapping("/message/reply")
    @ResponseBody
    public String admin_message(Long id,String admin_message){

        messageService.admin_message(id,admin_message);


        return "ok";
    }
}

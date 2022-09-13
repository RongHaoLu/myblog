package com.rh.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rh.blog.pojo.Message;
import com.rh.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FontMassageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/Messages")
    public ModelAndView messages(ModelAndView mv,Integer page){

        if(page==null){
            page=1;
        }

        PageHelper.startPage(page,15);
        List<Message> allMassage = messageService.findAllMassage();

        PageInfo<Message> pageInfo = new PageInfo<>(allMassage);
        long total = pageInfo.getTotal();

        int totalpage = (int) (Math.floor(total / 15) + 1);
        mv.addObject("totalpage",totalpage);
        mv.addObject("page",page);


        mv.addObject("messages",allMassage);
        mv.setViewName("message_board");
        System.out.println(allMassage);

        return mv;
    }

    @PostMapping("/saveMessage")
    @ResponseBody
    public String saveMessage(Message message){

        if (message.getNickname().equals("葫芦娃娃")||message.getNickname().equals("葫芦爸爸")||message.getNickname().equals("葫芦爷爷")
                ||message.getNickname().equals("葫芦娃他爸")){
            System.out.println(message.getNickname());
            return "博主专属名称不可用";
        }
        messageService.saveMessage(message);

        return "等待葫芦娃娃审核";
    }

}

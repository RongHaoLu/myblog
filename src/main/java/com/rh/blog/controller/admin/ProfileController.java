package com.rh.blog.controller.admin;

import com.rh.blog.pojo.User;
import com.rh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/rh")
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String Profile(){

        return "admin/profile";
    }



    @PostMapping("/profile/name")
    @ResponseBody
    public String profile_name(User user,HttpSession session){

        User sessionUser = (User) session.getAttribute("user");

        user.setId(sessionUser.getId());

        userService.update_name(user);

        return "success";
    }

    @PostMapping("/profile/password")
    @ResponseBody
    public String profile_password(User user,String newPassword,HttpSession session){

        User sessionUser = (User) session.getAttribute("user");

        User checkUser = userService.checkUser(sessionUser.getUsername(), user.getPassword());
        if(checkUser==null){
            return "no";
        }
        user.setId(sessionUser.getId());
        user.setPassword(newPassword);

        userService.update_password(user);

        return "success";
    }


}

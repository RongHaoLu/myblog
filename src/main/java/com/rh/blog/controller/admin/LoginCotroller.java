package com.rh.blog.controller.admin;

import com.rh.blog.pojo.User;
import com.rh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/rh")
public class LoginCotroller {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){

        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){

        User user = userService.checkUser(username,password);
        if(user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "redirect:/rh/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/rh";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){

        session.removeAttribute("user");
        return "redirect:/rh";
    }

}

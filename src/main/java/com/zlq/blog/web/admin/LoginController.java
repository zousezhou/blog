package com.zlq.blog.web.admin;


import com.zlq.blog.pojo.User;
import com.zlq.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class LoginController {

    public  static Long userId;

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    /*登入功能，用户校验 正确跳转到index页面*/
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession httpSession, RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            userId = user.getId();
            httpSession.setAttribute("user", user);
            return "admin/index";
        }
        attributes.addFlashAttribute("message","账号或密码错误！");
        return "redirect:/admin";
    }

    /*跳转到注册页面*/
    @GetMapping("/toRegist")
    public String toRegist(){
        return "admin/regist";
    }


    /*//注册 用户校验 成功后跳转到登录页面*/
    @PostMapping("/regist")
    public String regist(User user,Model model,RedirectAttributes attributes){
        User user2 = userService.checkUserByUsername(user.getUsername());
        if (null != user2){
            model.addAttribute("message","账号已存在！");
            return "/admin/regist";
        }
        user.setCreateTime(new Date());
        userService.saveUser(user);
        model.addAttribute("message","注册成功！");
        //attributes.addAttribute("message", "注册成功！");
        return "admin/login";


    }


    /*//注销功能 清楚session中的user后跳登入页面*/
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:admin";
    }

}

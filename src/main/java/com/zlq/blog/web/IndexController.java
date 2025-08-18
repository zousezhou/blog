package com.zlq.blog.web;


import com.zlq.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestAttributes;

@Controller
public class IndexController {

    @GetMapping("")
    public String index() {

        return "index";
    }

    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}

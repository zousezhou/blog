package com.zlq.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by lanqZhou on 2020.10.22
 */
@Controller
@RequestMapping("/admin")
public class typeController {

    @GetMapping("/types")
    public String list(){
        return "admin/types";
    }

}

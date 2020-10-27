package com.zlq.blog.web.admin;

import com.zlq.blog.pojo.Type;
import com.zlq.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Create by lanqZhou on 2020.10.22
 */
@Controller
@RequestMapping("/admin/types")
public class typeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    @GetMapping("/input")
    public String input(){
        return "admin/types-input";
    }

    @PostMapping("/insert")
    public String insertType(Type type, RedirectAttributes redirectAttributes){
        Type t = typeService.saveType(type);
        if (t==null){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }else{
            redirectAttributes.addFlashAttribute("message","成功！");
        }
        return "redirect:/admin/types";
    }



}

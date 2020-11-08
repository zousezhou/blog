package com.zlq.blog.web.admin;

import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Type;
import com.zlq.blog.service.TypeService;
import com.zlq.blog.util.URLSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;


/**
 * Create by lanqZhou on 2020.10.22
 */
@Controller
@RequestMapping("/admin/types")
public class TypeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping("/*/update")
    public String updateType(Model model){
        Long id = URLSessionUtil.getId();
        Type type = typeService.getType(id);
        model.addAttribute("name",type.getName());
        model.addAttribute("id",id);
        return "admin/types-input";
    }




    @GetMapping("/*/input")
    public String deleteType(RedirectAttributes redirectAttributes){

        try {
            Long id = URLSessionUtil.getId();
            typeService.deleteType(id);
            redirectAttributes.addFlashAttribute("message","删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/types";
    }

    @PostMapping("/insert")
    public String insertType(Type type, RedirectAttributes redirectAttributes){
        Type t = null;
        try {
            t = typeService.saveType(type);
        }catch (IllegalOperationException e){
            if(type.getId()!=null){
                redirectAttributes.addFlashAttribute("id",type.getId());
                redirectAttributes.addFlashAttribute("name",type.getName());
            }
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/admin/types/input";
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        if (t!=null){
            redirectAttributes.addFlashAttribute("message","成功！");
        }
        return "redirect:/admin/types";
    }

}

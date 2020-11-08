package com.zlq.blog.web.admin;

import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Tag;
import com.zlq.blog.service.TagService;
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


/**
 * Create by lanqZhou on 2020.10.22
 */
@Controller
@RequestMapping("/admin/tags")
public class TagController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TagService tagService;

    @GetMapping
    public String list(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    @GetMapping("/input")
    public String input(){
        return "admin/tags-input";
    }

    @GetMapping("/*/update")
    public String updateTag(Model model){
        Long id = URLSessionUtil.getId();
        Tag tag = tagService.getTag(id);
        model.addAttribute("name",tag.getName());
        model.addAttribute("id",id);
        return "admin/tags-input";
    }




    @GetMapping("/*/input")
    public String deleteTag(RedirectAttributes redirectAttributes){

        try {
            Long id = URLSessionUtil.getId();
            tagService.deleteTag(id);
            redirectAttributes.addFlashAttribute("message","删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/tags";
    }

    @PostMapping("/insert")
    public String insertTag(Tag tag, RedirectAttributes redirectAttributes){
        Tag t = null;
        try {
            t = tagService.saveTag(tag);
        }catch (IllegalOperationException e){
            if(tag.getId()!=null){
                redirectAttributes.addFlashAttribute("id",tag.getId());
                redirectAttributes.addFlashAttribute("name",tag.getName());
            }
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/admin/tags/input";
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        if (t!=null){
            redirectAttributes.addFlashAttribute("message","成功！");
        }
        return "redirect:/admin/tags";
    }


}

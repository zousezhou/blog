package com.zlq.blog.web.admin;

import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Blog;
import com.zlq.blog.service.BlogService;
import com.zlq.blog.service.TypeService;
import com.zlq.blog.util.URLSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.ws.Service;

/**
 * Create by lanqZhou on 2020.10.22
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping
    public String list(@PageableDefault(size = 10,sort = {"id"},
            direction = Sort.Direction.DESC)Pageable pageable,Blog blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10,
            sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable,
                     Blog blog,Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/input")
    public String blogInput(){
        return "admin/blogs-input";
    }


    @GetMapping("/*/update")
    public String updateBlogInput(Model model){
        Long id = URLSessionUtil.getId();
        Blog blog = blogService.getBlog(id);
        model.addAttribute("blog",blog);
        return "admin/blog-input";
    }


    @GetMapping("/*/delete")
    public String deleteBlog(RedirectAttributes redirectAttributes){
        try {
            Long id = URLSessionUtil.getId();
            blogService.deleteBlog(id);
            redirectAttributes.addFlashAttribute("message","删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/admin/blogs";
    }


    @GetMapping("/*/save")
    public String saveBlog(Blog blog,Model model,RedirectAttributes redirectAttributes) {
        Boolean published = blog.isPublished();
        Blog b = null;
        try {
           b = blogService.saveBlog(blog);
        }catch (IllegalOperationException illegalOperationException){
            redirectAttributes.addFlashAttribute("message",illegalOperationException.getMessage());
            redirectAttributes.addFlashAttribute("Blog",blog);
            return "redirect:/admin/blogs";
        }catch (Exception e) {
            e.printStackTrace();
        }

        if (published){
            return "redirect:/admin/blogs";
        }
        
        model.addAttribute("Blog",blog);
        return "admin/blogs/input";
    }

}

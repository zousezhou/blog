package com.zlq.blog.web.admin;

import com.zlq.blog.exception.IllegalOperationException;
import com.zlq.blog.pojo.Blog;
import com.zlq.blog.pojo.User;
import com.zlq.blog.service.BlogService;
import com.zlq.blog.service.TagService;
import com.zlq.blog.service.TypeService;
import com.zlq.blog.util.URLSessionUtil;
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

import javax.servlet.http.HttpSession;

/**
 * Create by lanqZhou on 2020.10.22
 */
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {

    private final String BLOGS = "admin/blogs";
    private final String BLOGS_INPUT = "admin/blogs-input";
    private final String REDIRECT_BLOGS = "redirect:/admin/blogs";
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    /*博客list页面 查询所有博客*/
    @GetMapping
    public String list(@PageableDefault(size = 10, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable,
                       Blog blog, Model model, HttpSession httpSession) {
        Long userId;
        User user = (User) httpSession.getAttribute("user");
        if (null != user){
            userId = user.getId();
        }
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return BLOGS;

    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model) {
        model.addAttribute("page", blogService.listBlog(pageable, blog));
        return "admin/blogs :: blogList";
    }

    @GetMapping("/input")
    public String blogInput(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        return BLOGS_INPUT;
    }


    @GetMapping("/*/update")
    public String updateBlogInput(Model model) {
        Long id = URLSessionUtil.getId();
        Blog blog = blogService.getBlog(id);
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", blog);
        return BLOGS_INPUT;
    }


    @GetMapping("/*/delete")
    public String deleteBlog(RedirectAttributes redirectAttributes) {
        try {
            Long id = URLSessionUtil.getId();
            blogService.deleteBlog(id);
            redirectAttributes.addFlashAttribute("message", "删除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        return REDIRECT_BLOGS;
    }


    @PostMapping("/save")
    public String saveBlog(Blog blog, Model model, RedirectAttributes redirectAttributes) {
        boolean published = blog.isPublished();
        Blog b = null;
        try {
            b = blogService.saveBlog(blog);
        } catch (IllegalOperationException illegalOperationException) {
            redirectAttributes.addFlashAttribute("message", illegalOperationException.getMessage());
            redirectAttributes.addFlashAttribute("Blog", blog);
            return REDIRECT_BLOGS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (published) {
            return REDIRECT_BLOGS;
        }
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("Blog", b);
        return BLOGS_INPUT;
    }

}

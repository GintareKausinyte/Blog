package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.entities.Blog;
import lt.codeacademy.blog.services.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/private")
public class PrivateBlogController {

    private BlogService blogService;

    public PrivateBlogController(BlogService blogService) {
        this.blogService = blogService;
    }


    @PostMapping("/createblog")
    public String createBlog(@Valid Blog blog, BindingResult result, Model model){
        if(result.hasErrors()){
            return "createblogform";
        }
        Blog newBlog=blogService.createOrUpdateBlogPost(blog);
        model.addAttribute("blog", newBlog);
        return "redirect:/public/blogs";
    }

    @GetMapping("/updateblog/{id}/delete")
    public String deleteBlog(@PathVariable Long id, Model model){
        blogService.deleteBlogPost(id);
        List<Blog> blogs=blogService.getAllBlogPosts();
        model.addAttribute("blogs", blogs);
        return "redirect:/public/blogs";
    }
    @GetMapping("/updateblog/{id}")
    public String getUpdateBlogForm(@PathVariable Long id, Model model){
        Blog blog=blogService.getBlogPost(id);
        model.addAttribute("blog", blog);
        return "updateblogform";
    }
    @GetMapping("/createblog")
    public String getCreateBlogForm(Model model){
        model.addAttribute("blog", new Blog());
        return "createblogform";
    }
    @PostMapping("/updateblog/{id}")
    public String updateBlog(@Valid Blog blog, BindingResult result, Model model){
        if(result.hasErrors()){
            return "updateblogform";
        }
        Blog newBlog=blogService.createOrUpdateBlogPost(blog);
        model.addAttribute("blog", newBlog);
        return "redirect:/public/blogs";
    }

}

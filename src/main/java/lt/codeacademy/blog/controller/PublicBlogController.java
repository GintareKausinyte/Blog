package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.entities.Blog;
import lt.codeacademy.blog.services.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/public")
public class PublicBlogController {
    private BlogService blogService;

    public PublicBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public String getAllBlogsByPage(@RequestParam(defaultValue = "0") int pageNumber, Model model) {
        Page<Blog> blogs=blogService.getAllBlogsPaginated(pageNumber);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("hasNextPage", blogs.hasNext());
        model.addAttribute("blogs", blogs.getContent());
        return "bloglist";
    }
    @GetMapping("/blogs/readblog/{id}")
    public String readBlog(@PathVariable Long id, Model model){
        Blog blog=blogService.getBlogPost(id);
        model.addAttribute("blog", blog);
        return "readblog";
    }
}

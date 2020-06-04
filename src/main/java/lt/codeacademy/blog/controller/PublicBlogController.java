package lt.codeacademy.blog.controller;

import lt.codeacademy.blog.entities.Blog;
import lt.codeacademy.blog.entities.User;
import lt.codeacademy.blog.services.BlogService;
import lt.codeacademy.blog.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/public")
public class PublicBlogController {
    private BlogService blogService;
    MyUserDetailsService userService;

    public PublicBlogController(BlogService blogService, MyUserDetailsService userService) {

        this.blogService = blogService;
        this.userService=userService;
    }

    @GetMapping("/registration")
    public String registrationform(Model model){
        model.addAttribute("user", new User());
        return "registrationform";
    }

    @PostMapping("/registration")
        public String newUser(@Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "registrationform";
        }
        User newUser =userService.newUser(user);
        return "redirect:/signIn"; }



    @GetMapping("/blogs")
    public String getAllBlogsByPage(@RequestParam(defaultValue = "0") int pageNumber, Model model, @AuthenticationPrincipal User user) {
        Page<Blog> blogs=blogService.getAllBlogsPaginated(pageNumber);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("hasNextPage", blogs.hasNext());
        model.addAttribute("blogs", blogs.getContent());
        model.addAttribute("user", user);
        return "bloglist";
    }
    @GetMapping("/blogs/readblog/{id}")
    public String readBlog(@PathVariable Long id, Model model){
        Blog blog=blogService.getBlogPost(id);
        model.addAttribute("blog", blog);
        return "readblog";
    }
}

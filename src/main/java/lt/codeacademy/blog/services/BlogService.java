package lt.codeacademy.blog.services;


import lt.codeacademy.blog.entities.Blog;
import lt.codeacademy.blog.controller.BlogPostNotFoundException;
import lt.codeacademy.blog.repositories.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog getBlogPost(Long id){
        return blogRepository.findById(id)
                .orElseThrow(()->new BlogPostNotFoundException("Blog with id " + id + " is not found"));
    }

    public void deleteBlogPost(Long id){blogRepository.deleteById(id);}

    public Blog createOrUpdateBlogPost(Blog blogPost){return blogRepository.save(blogPost);}

    public List<Blog> getAllBlogPosts(){return blogRepository.findAll();}

    public Page<Blog> getAllBlogsPaginated(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 4);
        return blogRepository.findAll(pageable);
    }

}

package com.example.lab12.Controller;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/get")
    public ResponseEntity getAllTodos(){
        return ResponseEntity.status(200).body(blogService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user , @RequestBody @Valid Blog blog){
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(200).body("Blog added");
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user , @RequestBody @Valid Blog blog, @PathVariable Integer blogId){
         blogService.updateBlog(user.getId(), blogId ,blog);
        return ResponseEntity.status(200).body("Blog updated");
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity updateTodos(@AuthenticationPrincipal User user , @PathVariable Integer blogId){
        blogService.deleteBlog(user.getId(), blogId);
        return ResponseEntity.status(200).body("Blog deleted");
    }

    @GetMapping("/getuser")
    public ResponseEntity getUserBlogs(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getUserBlogs(user.getId()));
    }

    @GetMapping("/getid/{blogId}")
    public ResponseEntity getBlogById(@PathVariable Integer blogId){
        return ResponseEntity.status(200).body(blogService.getBlogById(blogId));
    }

    @GetMapping("/gettitle/{title}")
    public ResponseEntity getBlogByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title));
    }

    }

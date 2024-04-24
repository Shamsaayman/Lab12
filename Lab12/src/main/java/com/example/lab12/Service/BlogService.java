package com.example.lab12.Service;
import com.example.lab12.API.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.AuthRepository;
import com.example.lab12.Repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthRepository authRepository;

    public List<Blog> findAll() {

        return blogRepository.findAll();
    }


    public void addBlog(Integer userId , Blog blog) {
        User user = authRepository.findUserById(userId);
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer userId, Integer blogId , Blog blog){
        Blog b = blogRepository.findBlogById(blogId);
        if(b==null){
            throw new ApiException("Blog not found");
        }
        if(b.getUser().getId() != userId){
            throw new ApiException("Blog not found in user");
        }
        b.setTitle(blog.getTitle());
        b.setBody(blog.getBody());
        blogRepository.save(b);
    }

    public void deleteBlog(Integer userId, Integer blogId){
        Blog b = blogRepository.findBlogById(blogId);
        if(b==null){
            throw new ApiException("Blog not found");
        }
        blogRepository.delete(b);
    }


    public List<Blog> getUserBlogs(Integer userId) {
        User user = authRepository.findUserById(userId);
        return blogRepository.findAllByUser(user);
    }

    public Blog getBlogById(Integer blogId){
        Blog b = blogRepository.findBlogById(blogId);
        if(b==null){
            throw new ApiException("Blog not found");
        }
        return b;
    }

    public Blog getBlogByTitle(String title){
        Blog b = blogRepository.findBlogByTitle(title);
        if(b==null){
            throw new ApiException("Blog title not found");
        }
        return b;
    }
}

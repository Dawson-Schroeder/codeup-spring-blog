package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Comment;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.CommentRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;
    private final CommentRepository commentsDao;

    public PostController(PostRepository postsDao, UserRepository usersDao, CommentRepository commentsDao){
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.commentsDao = commentsDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model){
        Post post = postsDao.findById(id);
        User user = usersDao.findUserById(post.getUser().getId());
        model.addAttribute("post", post);
        model.addAttribute("user", user);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createForm(){
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String submitForm(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body){
        Post post = new Post(title, body);
        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/comment")
    public String submitComment(@RequestParam(name = "content") String content, @RequestParam(name = "postId") long postId){
        Post post = postsDao.findById(postId);
        Comment comment = new Comment(content, post);
        commentsDao.save(comment);
        return "redirect:/posts";
    }




}

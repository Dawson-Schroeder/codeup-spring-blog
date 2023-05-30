package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostRepository postsDao;

    public PostController(PostRepository postsDao){
        this.postsDao = postsDao;
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
        model.addAttribute("post", post);
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




}

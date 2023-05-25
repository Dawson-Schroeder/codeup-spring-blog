package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String allPosts(Model model){
        ArrayList<Post> posts = new ArrayList<>();
        Post post1 = new Post("title1", "body1");
        Post post2 = new Post("title2", "body2");
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts );
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(Model model){
        Post post = new Post("onlyPost", "lonelyBody");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createForm(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitForm(){
        return "create a new post";
    }



}

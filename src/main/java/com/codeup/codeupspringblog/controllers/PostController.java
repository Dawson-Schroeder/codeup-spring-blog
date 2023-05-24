package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String allPosts(){
        return "Posts Index Page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(){
        return "view an individual post";
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

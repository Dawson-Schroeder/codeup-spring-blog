package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserRepository usersDao;
    private final PasswordEncoder passwordEncoder;
    public UserController(UserRepository usersDao, PasswordEncoder passwordEncoder){
        this.usersDao = usersDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "/login";
    }
    @GetMapping("/user/{id}/posts")
    public String userPosts(@PathVariable long id, Model model){
        User user = usersDao.findUserById(id);
        model.addAttribute("userPosts", user.getPosts());
        return "/users_posts";
    }

    @GetMapping("/register")
    public String registration(){
        return "/register";
    }
    @PostMapping("/register")
    public String registerUser(@RequestParam(name="username") String username, @RequestParam(name="email") String email, @RequestParam(name = "password") String password){
        password = passwordEncoder.encode(password);
        usersDao.save(new User(username, email, password));
        return "redirect:/posts/create";
    }

    @GetMapping("/profile")
    public String showProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        model.addAttribute("user", user);
        System.out.println(user.getUsername());
        return "profile";
    }

    @PostMapping("/profile")
    public String changeProfile(@RequestParam(name="email") String email){
        System.out.println("Post mapping hit");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        user = usersDao.findUserById(userId);
        user.setEmail(email);
        usersDao.save(user);
        return "redirect:/profile";
    }
}

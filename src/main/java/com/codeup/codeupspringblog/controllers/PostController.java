package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Comment;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.CommentRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PostController {

    private PostRepository postsDao;
    private UserRepository usersDao;
    private CommentRepository commentsDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository usersDao, CommentRepository commentsDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.commentsDao = commentsDao;
        this.emailService = emailService;
    }

//    public Set<Category> makeCategorySet(String categriesCsl){
////        create empty list of tag objects
//        Set<Category> categoryObjects = new HashSet<>();
////        if user didnt enter anything return an empty set
//        if (categriesCsl.equals("")){
//            return categoryObjects;
//        }
////        Create an array of strings and loop over it
//        for (String category : categriesCsl.split(",")){
//            Category categoryObject = new Category(category.trim());
//            categoryObjects.add(categoryObject);
//        }
//        return categoryObjects;
//    }

    @GetMapping("/posts")
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();
        Collections.reverse(allPosts);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String individualPost(@PathVariable long id, Model model) {
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "posts/create";
    }
    @PostMapping("/posts/{id}/edit")
    public String submitEdit(@PathVariable long id, @ModelAttribute Post post){
        User user = usersDao.findUserById(1L);
        post.setUser(user);
        post.setId(id);
        postsDao.save(post);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/create")
    public String createForm(Model model){
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String submitForm(@ModelAttribute Post post) {
        User user = usersDao.findUserById(1L);
        post.setUser(user);
        postsDao.save(post);
        emailService.prepareAndSend(post, "A new post has been POSTED", "The message would go here, and be much cooler if I wanted.", "jason.merrell@codeup.com");
        return "redirect:/posts";
    }

//    @PostMapping("/posts/create")
//    public String submitForm(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @RequestParam(name="categories") String categories){
//        User user = usersDao.findUserById(1L);
//        Post post = new Post(title, body, user);
//        Set<Category> categorySet = makeCategorySet(categories);
//        if (categorySet.size() > 0){
//            List<Category> categoriesToAdd = new ArrayList<>();
//            for(Category category : categorySet){
//                Category categoryFromDb = categoriesDao.findCategoryByName(category.getName());
//                if(categoryFromDb == null){
//                    categoriesToAdd.add(category);
//                } else {
//                    categoriesToAdd.add(categoryFromDb);
//                }
//            }
//            categorySet.clear();
//            categorySet.addAll(categoriesToAdd);
//            post.setCategories(categorySet);
//        }
//        postsDao.save(post);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/comment")
    public String submitComment(@RequestParam(name = "content") String content, @RequestParam(name = "postId") long postId){
        Post post = postsDao.findById(postId);
        User user = usersDao.findUserById(1L);
        Comment comment = new Comment(content, post, user);
        commentsDao.save(comment);
        return "redirect:/posts";
    }




}

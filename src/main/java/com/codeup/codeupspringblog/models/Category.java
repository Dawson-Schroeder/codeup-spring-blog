package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 100, unique = true)
    private String name;
    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }


    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
}

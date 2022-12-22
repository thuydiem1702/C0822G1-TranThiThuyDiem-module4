package com.ss7.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private Set<Blog> blogs;


    public Category() {
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(Integer id, String name, Set<Blog> blogs) {
        this.id = id;
        this.name = name;
        this.blogs = blogs;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public String getListBlogsAccordingToCategory() {
        List<Blog> blogDummies =  new ArrayList<>(this.blogs);
        StringBuilder listBlogName = new StringBuilder();

        for (int i = 0; i < blogDummies.size(); i++) {
            listBlogName.append(blogDummies.get(i).getName());
            if (i != blogDummies.size() - 1) {
                listBlogName.append(", ");
            }
        }

        return listBlogName.toString();
    }
}

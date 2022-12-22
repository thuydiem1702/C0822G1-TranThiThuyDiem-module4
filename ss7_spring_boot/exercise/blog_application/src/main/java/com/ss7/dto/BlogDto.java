package com.ss7.dto;

import java.time.LocalDate;

public class BlogDto {
    private Integer id;
    private String name;
    private String author;
    private LocalDate datePublished;
    private String content;
    private Integer categoryId;

    public BlogDto() {
    }

    public BlogDto(Integer id, String name, String author, LocalDate datePublished,
                   String content, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.datePublished = datePublished;
        this.content = content;
        this.categoryId = categoryId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}

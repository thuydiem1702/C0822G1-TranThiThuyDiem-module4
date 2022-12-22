package com.ss7.service.blog.impl;

import com.ss7.model.Blog;
import com.ss7.repository.IBlogRepository;
import com.ss7.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService {

    @Autowired
    IBlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Page<Blog> findAllBlogSorted(Pageable pageable) {
        return blogRepository.findByOrderByDatePublishedAsc(pageable);
    }

    @Override
    public Page<Blog> findAllByName(String nameSearch, Pageable pageable) {
        return blogRepository.findAllByName(nameSearch, pageable);
    }

    @Override
    public List<Blog> findAllBasedOnCategory(Integer idCategory) {
        return blogRepository.findAllBasedOnCategory(idCategory);
    }

    @Override
    public List<Blog> findByAuthor(String authorName) {
        return blogRepository.findByAuthor(authorName);
    }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Integer id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Integer id) {
        blogRepository.deleteById(id);
    }
}

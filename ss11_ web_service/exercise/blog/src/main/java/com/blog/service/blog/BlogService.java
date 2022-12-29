package com.blog.service.blog;

import com.blog.model.Blog;
import com.blog.repository.IBlogRepository;
import com.blog.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements IBlogService{
    @Autowired
    IBlogRepository iBlogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return iBlogRepository.findAll(pageable);
    }

    @Override
    public Optional<Blog> deleteById(int id) {
        iBlogRepository.deleteById(id);
        return null;
    }

    @Override
    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public Optional<Blog> findById(int id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public List<Blog> listSearchByName(String name) {
        return iBlogRepository.listSearchByName(name);
    }
}
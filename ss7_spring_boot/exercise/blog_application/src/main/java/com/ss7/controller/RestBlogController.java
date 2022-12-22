package com.ss7.controller;

import com.ss7.dto.BlogDto;
import com.ss7.model.Blog;
import com.ss7.service.blog.IBlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/blog")
public class RestBlogController {

    @Autowired
    private IBlogService blogService;

    @GetMapping
    public ResponseEntity<Iterable<BlogDto>> getBlogList() {
        List<Blog> blogList = (List<Blog>) blogService.findAll();

        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BlogDto> blogListDto = new ArrayList<>();

        for (Blog x : blogList) {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(x, blogDto);
            blogDto.setCategoryId(x.getCategory().getId());
            blogListDto.add(blogDto);
        }


        return new ResponseEntity<>(blogListDto, HttpStatus.OK);
    }

    @GetMapping("/category-{id}")
    public ResponseEntity<Iterable<Blog>> getBlogListOfOneCategory(@PathVariable Integer id) {
        List<Blog> blogList = blogService.findAllBasedOnCategory(id);

        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> viewBlog(@PathVariable Integer id) {
        Optional<Blog> blogViewed = blogService.findById(id);

        if (!blogViewed.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(blogViewed.get(), HttpStatus.OK);
    }

    @GetMapping("/searchByAuthor/{authorName}")
    public ResponseEntity<List<BlogDto>> findByProducer(@PathVariable String authorName) {
        List<Blog> blogList =  blogService.findByAuthor(authorName);

        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<BlogDto> blogListDto = new ArrayList<>();

        for (Blog x : blogList) {
            BlogDto blogDto = new BlogDto();
            BeanUtils.copyProperties(x, blogDto);
            blogDto.setCategoryId(x.getCategory().getId());
            blogListDto.add(blogDto);
        }


        return new ResponseEntity<>(blogListDto, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<List<Blog>> findAll(@PageableDefault(value = 3) Pageable pageable) {
        Page<Blog> blogList = blogService.findAll(pageable);

        return new ResponseEntity<>(blogList.getContent(), HttpStatus.OK);
    }
}

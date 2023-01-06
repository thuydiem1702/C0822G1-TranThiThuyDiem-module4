package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.Category;
import com.blog.service.blog.IBlogService;
import com.blog.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
@CrossOrigin("*")
public class BlogController {
    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("")
    public ResponseEntity<Page<Blog>> list(Model model , @PageableDefault(size = 2) Pageable pageable) {
        Page<Blog> blogList = iBlogService.findAll(pageable);
        if (blogList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList,HttpStatus.OK);
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<Blog> delete(@PathVariable int id, Model model) {
        Optional<Blog> blogOptional = iBlogService.findById(id);
        if (blogOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iBlogService.deleteById(id);
        return new ResponseEntity<>(blogOptional.get(),HttpStatus.NO_CONTENT);
    }

    @GetMapping("/create")
    public String showFormCreate(Model model ){
        List<Category> categoryList = iCategoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("blog",new Blog());
        return "views/create";
    }

    @PostMapping("/create")
    public String create(Blog blog ,Model model){
        iBlogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable("id") int id, Model model){
        List<Category> categoryList = iCategoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("blog",iBlogService.findById(id));
        return "views/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("blog") Blog blog){
        iBlogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<Optional<Blog>> view(@PathVariable("id") int id, Model model) {
        Optional<Blog> blogOptional= iBlogService.findById(id);
        if (!blogOptional.isPresent()){
            return new ResponseEntity<>(blogOptional,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogOptional,HttpStatus.OK);


    }

    @GetMapping("/search")
    public String searchByNameAndEmailAndCustomerType(String name,Model model){
        List<Blog> blogList = iBlogService.listSearchByName(name);
        model.addAttribute("blogList",blogList);
        return "redirect:/blog";
    }
}
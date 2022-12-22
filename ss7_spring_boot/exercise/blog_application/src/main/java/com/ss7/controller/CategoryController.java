package com.ss7.controller;

import com.ss7.model.Category;
import com.ss7.service.category.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public String showListCategory(Model model) {

        model.addAttribute("categoryList", categoryService.findAll());

        return "/blog/category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("categoryNew", new Category());

        return "/blog/category/formCreate";
    }

    @PostMapping("/create")
    public String showCreateForm(@ModelAttribute Category categoryNew, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mess", "Add new category successfully!");
        categoryService.save(categoryNew);

        return "redirect:/category/list";
    }

    @GetMapping("/delete-category/{idDelete}")
    public String showDeletePage(@PathVariable Integer idDelete, Model model) {
        Optional<Category> categoryDelete = categoryService.findById(idDelete);
        if (categoryDelete.isPresent()) {
            model.addAttribute("categoryDelete", categoryDelete.get());
            return "/blog/category/delete";
        } else {
            return "/blog/error";
        }
    }

    @PostMapping("/delete-category")
    public String deleteCategory(@ModelAttribute Category categoryDelete, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("mess", "Delete category successfully!");

        categoryService.deleteBlog(categoryDelete.getId());

        return "redirect:/category/list";
    }


    @GetMapping("/edit-category/{idEdit}")
    public String showEditForm(Model model,@PathVariable Integer idEdit) {
        Optional<Category> categoryEdit = categoryService.findById(idEdit);

        if (categoryEdit.isPresent()) {
            model.addAttribute("categoryEdit", categoryEdit.get());
            return "/blog/category/formEdit";
        } else {
            return "/blog/error";
        }
    }

    @PostMapping("/edit")
    public String editBlog(@ModelAttribute Category categoryEdit, RedirectAttributes redirectAttributes) {
        categoryService.save(categoryEdit);

        redirectAttributes.addFlashAttribute("mess","Category Edited successfully!");

        return "redirect:/category/list";
    }

}

package com.controller;

import com.model.Product;
import com.service.IProductService;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService = new ProductService();

    @GetMapping("/list")
    public String toIndex(Model model) {
        List<Product> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        return "/product/list";
    }

    @GetMapping("/showAddNewForm")
    public String showAddNewForm(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/product/create";
    }

    @PostMapping("/addNewProduct")
    public String addNewProduct(@ModelAttribute Product newProduct, RedirectAttributes redirectAttributes) {
        String mess = "Add new product failed!";

        if (productService.addProduct(newProduct)) {
            mess = "Adding new product Succesfully!";
        }

        redirectAttributes.addFlashAttribute("mess", mess);
        return "redirect:/product/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(Model model, @RequestParam int idFind) {
        Product productUpdate = productService.findProductById(idFind);

        model.addAttribute("productUpdate", productUpdate);

        return "/product/edit";
    }

    @PostMapping("/editProduct")
    public String editProduct(@ModelAttribute Product productUpdate, RedirectAttributes redirectAttributes) {
        String mess = null;

        if (productService.updateProduct(productUpdate)) {
            mess = "Product updated successfully!";
        }

        redirectAttributes.addFlashAttribute("mess", mess);
        return "redirect:/product/list";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam int idDelete, RedirectAttributes redirectAttributes) {

        productService.deleteProduct(idDelete);

        redirectAttributes.addFlashAttribute("mess", "Delete product successfully!");
        return "redirect:/product/list";
    }

    @GetMapping("/viewProduct")
    public String viewProduct(@RequestParam int idView, Model model) {
        Product productView = productService.findProductById(idView);

        List<Product> productToView = new ArrayList<>();

        productToView.add(productView);

        model.addAttribute("productList", productToView);

        return "/product/list";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam String productNameSearch, Model model) {
        String mess = "Found!";

        List<Product> productFound = productService.searchByName(productNameSearch);

        if (productFound.size() == 0) {
            mess = "No products found!";
        }

        model.addAttribute("productList", productFound);
        model.addAttribute("mess", mess);

        return "/product/list";
    }


}
package com.ss9.book.controller;

import com.ss9.book.model.Book;
import com.ss9.book.model.OrderBook;
import com.ss9.book.model.Title;
import com.ss9.book.service.book.IBookService;
import com.ss9.book.service.orderBook.IOrderBookService;
import com.ss9.book.service.title.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/title")
public class TitleController {
    @Autowired
    private IOrderBookService orderBookService;

    @Autowired
    private IBookService bookService;

    @Autowired
    private ITitleService titleService;

    @GetMapping("/list")
    public String showListTitle(Model model) {

        model.addAttribute("titleList", titleService.findAll());

        return "/title/list";
    }

    @GetMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Integer id,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        Optional<Title> title = titleService.findById(id);

        if (title.isPresent()) {
            Title titleAboutToBeBorrowed = title.get();

            if (titleAboutToBeBorrowed.getCount() == 0) {
                redirectAttributes.addFlashAttribute("mess", "Sorry, there are no " +
                        "more book of this title available");
                return "redirect:/title/list";
            } else {
                List<Book> bookFoundByTitleId = bookService.findBookByTitle(titleAboutToBeBorrowed.getId());

                Book bookAboutToBeBorrowed = bookFoundByTitleId.get(0);

                Optional<OrderBook> orderBookCheck;
                Integer otp;
                do {
                    otp = orderBookService.getOtp();

                    orderBookCheck = orderBookService.findByOtp(otp);

                } while (orderBookCheck.isPresent());


                OrderBook newOrderBook = new OrderBook(bookAboutToBeBorrowed,
                        otp, 1);

                orderBookService.save(newOrderBook);
                bookAboutToBeBorrowed.setStatus(0);
                bookService.save(bookAboutToBeBorrowed);

                model.addAttribute("mess", "Your otp code is: " + otp);
                model.addAttribute("idOrderBook", newOrderBook.getId());
                return "/title/otp";
            }

        }

        return "/title/error";
    }

    @PostMapping("/borrow")
    public String borrow(@RequestParam Integer otpCode,
                         @RequestParam Integer idOrderBook,
                         RedirectAttributes redirectAttributes) {

        Optional<OrderBook> orderBook = orderBookService.findByOtp(otpCode);

        if (orderBook.isPresent()) {
            Optional<Title> title = titleService.findById(orderBook.get().getBook().getTitle().getId());

            if (title.isPresent()) {
                Title titleSave = title.get();
                titleSave.setCount(titleSave.getCount() - 1);
                titleService.save(titleSave);
            }

            redirectAttributes.addFlashAttribute("mess", "Borrow Book Successfully!");
        } else {
            orderBookService.delete(idOrderBook);
            redirectAttributes.addFlashAttribute("mess", "Your otp is not matched!");
        }

        return "redirect:/title/list";
    }

    @GetMapping("/return")
    public String returnBook() {
        return "/title/returnBook";
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam Integer otpCode, RedirectAttributes redirectAttributes) {

        Optional<OrderBook> orderBook = orderBookService.findByOtp(otpCode);

        if (!orderBook.isPresent()) {
            redirectAttributes.addFlashAttribute("mess", "There is no such an Otp code");
        } else {
            OrderBook orderBookToReturn = orderBook.get();

            orderBookToReturn.setStatus(0);

            Optional<Title> title = titleService.findById(orderBook.get().getBook().getTitle().getId());

            Optional<Book> bookFindById = bookService.findById(otpCode);
            if (bookFindById.isPresent()) {
                Book bookToReturn = bookFindById.get();
                bookToReturn.setStatus(1);
                bookService.save(bookToReturn);
            }

            if (title.isPresent()) {
                Title titleSave = title.get();
                titleSave.setCount(titleSave.getCount() + 1);
                titleService.save(titleSave);
            }

            redirectAttributes.addFlashAttribute("mess", "Returning book successfully!");
        }

        return "redirect:/title/list";
    }

    @ExceptionHandler(Exception.class)
    public String handleError() {
        return "title/error";
    }
}

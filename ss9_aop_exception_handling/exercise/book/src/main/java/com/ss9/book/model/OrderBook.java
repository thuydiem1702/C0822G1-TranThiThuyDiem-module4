package com.ss9.book.model;

import javax.persistence.*;

@Entity
public class OrderBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

    private Integer otp;

    private int status;

    public OrderBook() {
    }

    public OrderBook(Book book, Integer otp, int status) {
        this.book = book;
        this.otp = otp;
        this.status = status;
    }

    public OrderBook(Integer id, Book book, Integer otp, int status) {
        this.id = id;
        this.book = book;
        this.otp = otp;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

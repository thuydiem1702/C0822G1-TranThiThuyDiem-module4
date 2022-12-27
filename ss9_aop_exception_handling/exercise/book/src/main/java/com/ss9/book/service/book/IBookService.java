package com.ss9.book.service.book;

import com.ss9.book.model.Book;
import com.ss9.book.service.IGeneralService;

import java.util.List;

public interface IBookService extends IGeneralService<Book> {
    List<Book> findBookByTitle(Integer titleId);
}

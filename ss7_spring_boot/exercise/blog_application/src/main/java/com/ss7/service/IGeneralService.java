package com.ss7.service;

import com.ss7.model.Blog;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.Optional;

public interface IGeneralService<T> {

    Iterable<T> findAll();

    Optional<T> findById(Integer id);

    void save(T t);

    void deleteBlog(Integer id);
}
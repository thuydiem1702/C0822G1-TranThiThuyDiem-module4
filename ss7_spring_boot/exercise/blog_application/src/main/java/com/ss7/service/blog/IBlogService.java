package com.ss7.service.blog;

import com.ss7.model.Blog;
import com.ss7.service.IGeneralService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService extends IGeneralService<Blog> {
    Page<Blog> findAll(Pageable pageable);

    Page<Blog> findAllBlogSorted(Pageable pageable);

    Page<Blog> findAllByName(String nameSearch, Pageable pageable);

    List<Blog> findAllBasedOnCategory(Integer idCategory);

    List<Blog> findByAuthor(String authorName);
}

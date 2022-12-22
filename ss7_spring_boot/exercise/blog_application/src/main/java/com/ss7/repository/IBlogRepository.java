package com.ss7.repository;

import com.ss7.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends JpaRepository<Blog, Integer> {

    @Query(
            value = "select * from blog order by (date_published) asc",
            nativeQuery = true)
    Page<Blog> findByOrderByDatePublishedAsc(Pageable pageable);

    @Query(
            value = "select * from blog where name like %:nameSearch%",
            nativeQuery = true)
    Page<Blog> findAllByName(@Param("nameSearch") String nameSearch, Pageable pageable);

    @Query(
            value = "select * from blog where category_id = :idCategory",
            nativeQuery = true)
    List<Blog> findAllBasedOnCategory(@Param("idCategory") Integer idCategory);


    @Query(
            value = "select * from blog where author like %:authorName%",
            nativeQuery = true)
    List<Blog> findByAuthor(@Param("authorName") String authorName);
}

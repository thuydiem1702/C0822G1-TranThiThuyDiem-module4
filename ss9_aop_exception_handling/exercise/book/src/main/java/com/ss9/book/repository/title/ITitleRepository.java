package com.ss9.book.repository.title;

import com.ss9.book.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITitleRepository extends JpaRepository<Title,Integer> {
    @Query(
            value = "UPDATE `book_manager_ss9`.`title` SET `count` = `count` - 1 WHERE (`id` = :titleId)",
            nativeQuery = true)
    @Modifying
    void decrementCount(@Param("titleId") Integer titleId);
}

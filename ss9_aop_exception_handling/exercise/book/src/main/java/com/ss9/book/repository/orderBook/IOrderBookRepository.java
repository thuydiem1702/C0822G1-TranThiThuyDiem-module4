package com.ss9.book.repository.orderBook;

import com.ss9.book.model.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IOrderBookRepository extends JpaRepository<OrderBook,Integer> {
    @Query(
            value = "select * from order_book where (`otp` = :otp)",
            nativeQuery = true)
    Optional<OrderBook> findByOtp(@Param("otp") Integer otp);
}

package com.ss9.book.service.orderBook;

import com.ss9.book.model.OrderBook;
import com.ss9.book.service.IGeneralService;

import java.util.Optional;

public interface IOrderBookService extends IGeneralService<OrderBook> {
    Optional<OrderBook> findByOtp(Integer otp);

    Integer getOtp();
}


package com.ss9.book.service.orderBook.impl;

import com.ss9.book.model.OrderBook;
import com.ss9.book.repository.orderBook.IOrderBookRepository;
import com.ss9.book.service.orderBook.IOrderBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class OrderBookService implements IOrderBookService {

    @Autowired
    private IOrderBookRepository orderBookRepository;

    @Override
    public Iterable<OrderBook> findAll() {
        return null;
    }

    @Override
    public Optional<OrderBook> findById(Integer id) {
        return orderBookRepository.findById(id);
    }

    @Override
    public void save(OrderBook orderBook) {
        orderBookRepository.save(orderBook);
    }

    @Override
    public void delete(Integer otpCode) {
        orderBookRepository.deleteById(otpCode);
    }

    @Override
    public Optional<OrderBook> findByOtp(Integer otp) {
        return orderBookRepository.findByOtp(otp);
    }

    @Override
    public Integer getOtp() {
        Random r = new Random( System.currentTimeMillis() );
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }
}

package com.ss9.book.service.title.impl;


import com.ss9.book.model.Title;
import com.ss9.book.repository.title.ITitleRepository;
import com.ss9.book.service.title.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TitleService implements ITitleService {

    @Autowired
    private ITitleRepository tileRepository;

    @Override
    public Iterable<Title> findAll() {
        return tileRepository.findAll();
    }

    @Override
    public Optional<Title> findById(Integer id) {
        return tileRepository.findById(id);
    }

    @Override
    public void save(Title title) {
        tileRepository.save(title);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void decreaseCount(Integer titleId) {
        tileRepository.decrementCount(titleId);
    }
}
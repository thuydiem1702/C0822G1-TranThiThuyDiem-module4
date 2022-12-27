package com.ss9.book.service.title;

import com.ss9.book.model.Title;
import com.ss9.book.service.IGeneralService;

public interface ITitleService extends IGeneralService<Title> {

    void decreaseCount(Integer titleId);
}


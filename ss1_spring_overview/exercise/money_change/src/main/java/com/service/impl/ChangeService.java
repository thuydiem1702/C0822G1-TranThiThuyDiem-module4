package com.service.impl;

import com.service.IChangeService;
import org.springframework.stereotype.Service;

@Service
public class ChangeService implements IChangeService {
    @Override
    public double change(double amount, double rate) {
        return amount * rate;
    }
}

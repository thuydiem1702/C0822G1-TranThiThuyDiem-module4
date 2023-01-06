package com.example.case_study___.service.customer.impl;

import com.example.case_study___.model.customer.CustomerType;
import com.example.case_study___.repository.customer.ICustomerTypeRepository;
import com.example.case_study___.service.customer.ICustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository iCustomerTypeRepository;

    @Override
    public List<CustomerType> findAll() {
        return iCustomerTypeRepository.findAll();
    }
}

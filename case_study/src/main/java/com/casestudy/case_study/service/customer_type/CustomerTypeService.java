package com.casestudy.case_study.service.customer_type;

import com.casestudy.case_study.model.customer.Customer;
import com.casestudy.case_study.repository.ICustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerTypeService implements ICustomerTypeService {
    @Autowired
    private ICustomerTypeRepository iCustomerTypeRepository;

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) iCustomerTypeRepository;
    }
}

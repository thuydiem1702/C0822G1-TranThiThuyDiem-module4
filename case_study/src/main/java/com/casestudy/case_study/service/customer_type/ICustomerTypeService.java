package com.casestudy.case_study.service.customer_type;

import com.casestudy.case_study.model.customer.Customer;
import com.casestudy.case_study.model.customer.CustomerType;

import java.util.List;
import java.util.Optional;

public interface ICustomerTypeService {
    List<Customer> findAll();

    Optional<CustomerType> findById(Integer valueOf);
}

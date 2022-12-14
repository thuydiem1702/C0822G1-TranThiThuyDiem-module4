package com.casestudy.case_study.service.customer;

import com.casestudy.case_study.dto.CustomerDto;
import com.casestudy.case_study.model.customer.Customer;
import com.casestudy.case_study.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ICustomerService extends IGeneralService<Customer> {
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> search(String searchName, String searchAddress,
                          String searchCustomerType, Pageable pageable);

    Map<String,String> messError(CustomerDto customerDto);

}
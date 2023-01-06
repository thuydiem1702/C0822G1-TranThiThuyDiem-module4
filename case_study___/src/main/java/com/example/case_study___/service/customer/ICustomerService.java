package com.example.case_study___.service.customer;

import com.example.case_study___.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findAll();

    void save(Customer customer);

    Optional<Customer> findById(Integer id);

    void update(Customer customer);

    void deleteLogical(Integer id);

    Page<Customer> searchCustomer(String nameSearch, String emailSearch, Pageable pageable);
}

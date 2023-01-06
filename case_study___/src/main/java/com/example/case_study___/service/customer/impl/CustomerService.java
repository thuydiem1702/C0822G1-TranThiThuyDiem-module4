package com.example.case_study___.service.customer.impl;

import com.example.case_study___.model.customer.Customer;
import com.example.case_study___.repository.customer.ICustomerRepository;
import com.example.case_study___.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public List<Customer> findAll() {
        return iCustomerRepository.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return iCustomerRepository.findById(id);
    }

    @Override
    public void update(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void deleteLogical(Integer id) {
        iCustomerRepository.deleteLogical(id);
    }

    @Override
    public Page<Customer> searchCustomer(String nameSearch, String emailSearch, Pageable pageable) {
        return iCustomerRepository.searchCustomer(nameSearch,emailSearch,pageable);
    }
}

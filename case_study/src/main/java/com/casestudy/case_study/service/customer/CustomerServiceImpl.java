package com.casestudy.case_study.service.customer;

import com.casestudy.case_study.dto.CustomerDto;
import com.casestudy.case_study.model.customer.Customer;
import com.casestudy.case_study.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void remove(Integer id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> search(String searchName, String searchAddress,
                                 String searchCustomerType, Pageable pageable) {
        return customerRepository.search(searchName, searchAddress, searchCustomerType, pageable);
    }

    @Override
    public Map<String, String> messError(CustomerDto customerDto) {
        Map<String, String> messError=new HashMap<>();
        for (Customer customer:customerRepository.findAll()) {
            if (customer.getIdCard().equals(customerDto.getIdCard())){
                messError.put("Id Card","Id Card đã tồn tại!!");
            }
            if (customer.getEmail().equals(customerDto.getEmail())){
                messError.put("Email","Email đã tồn tại!!");
            }
            if (customer.getPhoneNumber().equals(customerDto.getPhoneNumber())){
                messError.put("Số điện thoại","Số điện thoại đã tồn tại!!");
            }
        }
        return messError;
    }
}

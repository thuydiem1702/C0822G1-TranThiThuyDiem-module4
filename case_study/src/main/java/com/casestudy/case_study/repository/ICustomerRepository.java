package com.casestudy.case_study.repository;

import com.casestudy.case_study.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(
            value = "select * from customer where status = 1",
            nativeQuery = true
    )
    List<Customer> findAll();

    @Query(
            value = "select * from customer where status = 1",
            nativeQuery = true
    )
    Page<Customer> findAll(Pageable pageable);

    @Query(
            value = "select * from customer where name like %:searchName% " +
                    "and address like %:searchAddress% " +
                    "and customer_type_id like %:searchCustomerType% " +
                    "and status = 1",
            nativeQuery = true

    )
    Page<Customer> search(@Param("searchName") String searchName,
                          @Param("searchAddress") String searchAddress,
                          @Param("searchCustomerType") String searchCustomerType,
                          Pageable pageable);

}
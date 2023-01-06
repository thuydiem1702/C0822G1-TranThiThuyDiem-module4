package com.example.case_study___.repository.customer;

import com.example.case_study___.model.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select * from customer where customer_name like %:nameSearch% and " +
            "customer_email like %:emailSearch% and delete_status = false",
            nativeQuery = true)
    Page<Customer> searchCustomer(@Param("nameSearch") String nameSearch, @Param("emailSearch") String emailSearch,
                                  Pageable pageable);

    @Query(value = "select * from customer where delete_status = false", nativeQuery = true)
    List<Customer> findAll();

    @Modifying
    @Query(value = "update customer set delete_status = true where customer_id = :idDelete", nativeQuery = true)
    void deleteLogical(@Param("idDelete") Integer id);

}

package com.casestudy.case_study.repository;

import com.casestudy.case_study.model.contract.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IContractRepository extends JpaRepository<Contract, Integer> {
    @Query(value = "select " +
            " `contract`.* " +
            "from `contract` join `customer` on `contract`.customer_id = `customer`.id\n" +
            " join `facility` on `contract`.facility_id = `facility`.id " +
            "where `customer`.`name` like %:customerSearchName% and `facility`.`name` like %:facilitySearchName%  " +
            " and `facility`.`status` = 1 " +
            "  and `customer`.`status` = 1  " +
            "      and `contract`.`status` = 1",
            nativeQuery = true)
    Page<Contract> findAll(@Param("customerSearchName") String customerSearchName,
                           @Param("facilitySearchName") String facilitySearchName,
                           Pageable pageable);

    @Query(value = "select " +
            " `contract`.* " +
            "from `contract` join `customer` on `contract`.customer_id = `customer`.id\n" +
            " join `facility` on `contract`.facility_id = `facility`.id " +
            "where `customer`.`name` like %:customerSearchName% and `facility`.`name` like %:facilitySearchName%  " +
            " and `facility`.`status` = 1 " +
            " and `customer`.`status` = 1  " +
            " and `contract`.`status` = 1 " +
            " and `contract`.`end_date` > now() ",
            nativeQuery = true)
    Page<Contract> findAllCurrent(@Param("customerSearchName") String customerSearchName,
                                  @Param("facilitySearchName") String facilitySearchName,
                                  Pageable pageable);
}

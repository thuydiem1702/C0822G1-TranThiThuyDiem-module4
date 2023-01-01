package com.casestudy.case_study.repository;

import com.casestudy.case_study.model.contract.ContractDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IContractDetailRepository extends JpaRepository<ContractDetail, Integer> {
    @Query(
            value = "select * from contract_detail where status = 1",
            nativeQuery = true
    )
    List<ContractDetail> findAll();

    @Query(
            value = "select * from contract_detail where contract_id = :contractId and status = 1",
            nativeQuery = true
    )
    List<ContractDetail> findByContractIdForMoney(@Param("contractId") String contractId);

    @Query(
            value = "select * from contract_detail where contract_id = :contractId and status = 1 ",

            nativeQuery = true
    )
    List<ContractDetail> findByContractIdForAttachFacility(@Param("contractId") String contractId);


}

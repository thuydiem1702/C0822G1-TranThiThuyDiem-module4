package com.casestudy.case_study.service.contract;

import com.casestudy.case_study.model.contract.Contract;
import com.casestudy.case_study.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContractService extends IGeneralService<Contract> {
    Page<Contract> findAll(String customerSearchName, String facilitySearchName, Pageable pageable);

    String getTotalMoney(Integer contractId);

    Page<Contract> findAllCurrent(String customerSearchName,String facilitySearchName,Pageable pageable);
}

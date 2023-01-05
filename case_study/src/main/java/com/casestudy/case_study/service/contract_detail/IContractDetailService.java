package com.casestudy.case_study.service.contract_detail;

import com.casestudy.case_study.model.contract.ContractDetail;
import com.casestudy.case_study.service.IGeneralService;

import java.util.List;

public interface IContractDetailService extends IGeneralService<ContractDetail> {
    List<ContractDetail> findByContractIdForMoney(Integer contractId);

    List<ContractDetail> findByContractIdForAttachFacility(Integer contractId);
}

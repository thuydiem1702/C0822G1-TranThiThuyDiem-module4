package com.casestudy.case_study.service.contract;

import com.casestudy.case_study.model.contract.Contract;
import com.casestudy.case_study.model.contract.ContractDetail;
import com.casestudy.case_study.repository.IContractRepository;
import com.casestudy.case_study.service.contract_detail.IContractDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractServiceImpl implements IContractService {

    @Autowired
    private IContractDetailService contractDetailService;

    @Autowired
    private IContractRepository contractRepository;

    @Override
    public Iterable<Contract> findAll() {
        return null;
    }

    @Override
    public Optional<Contract> findById(Integer id) {
        return contractRepository.findById(id);
    }

    @Override
    public Contract save(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Page<Contract> findAll(String customerSearchName, String facilitySearchName, Pageable pageable) {
        return contractRepository.findAll(customerSearchName, facilitySearchName, pageable);
    }

    @Override
    public String getTotalMoney(Integer contractId) {
        Double totalMoney = 0.0;
        Double totalAttachFacilityMoney = 0.0;

        List<ContractDetail> contractDetailList = contractDetailService.findByContractIdForMoney(contractId);

        for (ContractDetail x : contractDetailList) {
            totalAttachFacilityMoney += (x.getAttachFacility().getCost() * x.getQuantity());
        }

        Optional<Contract> contractABTG = contractRepository.findById(contractId);

        if (contractABTG.isPresent()) {
            Contract contract = contractABTG.get();

            totalMoney = contract.getFacility().getCost() + totalAttachFacilityMoney;
        }

        return String.valueOf(totalMoney);
    }

    @Override
    public Page<Contract> findAllCurrent(String customerSearchName, String facilitySearchName, Pageable pageable) {
        return contractRepository.findAllCurrent(customerSearchName, facilitySearchName, pageable);
    }
}

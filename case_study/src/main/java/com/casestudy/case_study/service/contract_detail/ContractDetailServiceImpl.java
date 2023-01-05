package com.casestudy.case_study.service.contract_detail;

import com.casestudy.case_study.model.contract.ContractDetail;
import com.casestudy.case_study.repository.IContractDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractDetailServiceImpl implements IContractDetailService {

    @Autowired
    private IContractDetailRepository contractDetailRepository;

    @Override
    public Iterable<ContractDetail> findAll() {
        return contractDetailRepository.findAll();
    }

    @Override
    public Optional<ContractDetail> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public ContractDetail save(ContractDetail contractDetail) {
        return contractDetailRepository.save(contractDetail);
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<ContractDetail> findByContractIdForMoney(Integer contractId) {
        return contractDetailRepository.findByContractIdForMoney(String.valueOf(contractId));
    }

    @Override
    public List<ContractDetail> findByContractIdForAttachFacility(Integer contractId) {
        return contractDetailRepository.findByContractIdForAttachFacility(String.valueOf(contractId));
    }
}

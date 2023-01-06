package com.example.case_study___.service.facility.impl;

import com.example.case_study___.model.facility.RentType;
import com.example.case_study___.repository.customer.ICustomerRepository;
import com.example.case_study___.repository.facility.IRentTypeRepository;
import com.example.case_study___.service.facility.IRentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentTypeService implements IRentTypeService {
    @Autowired
    private IRentTypeRepository iRentTypeRepository;

    @Override
    public List<RentType> findAll() {
        return iRentTypeRepository.findAll();
    }
}

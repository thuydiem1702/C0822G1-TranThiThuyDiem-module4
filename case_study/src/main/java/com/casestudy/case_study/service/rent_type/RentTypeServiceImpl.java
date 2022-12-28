package com.casestudy.case_study.service.rent_type;

import com.casestudy.case_study.model.facility.RentType;
import com.casestudy.case_study.repository.IRentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RentTypeServiceImpl implements IRentTypeService{
    @Autowired
    private IRentTypeRepository rentTypeRepository;

    @Override
    public Iterable<RentType> findAll() {
        return rentTypeRepository.findAll();
    }

    @Override
    public Optional<RentType> findById(Integer id) {
        return rentTypeRepository.findById(id);
    }

    @Override
    public RentType save(RentType rentType) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}

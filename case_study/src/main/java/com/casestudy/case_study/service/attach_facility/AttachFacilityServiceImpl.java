package com.casestudy.case_study.service.attach_facility;

import com.casestudy.case_study.model.contract.AttachFacility;
import com.casestudy.case_study.repository.IAttachFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttachFacilityServiceImpl implements IAttachFacilityService{

    @Autowired
    private IAttachFacilityRepository attachFacilityRepository;

    @Override
    public Iterable<AttachFacility> findAll() {
        return attachFacilityRepository.findAll();
    }

    @Override
    public Optional<AttachFacility> findById(Integer id) {
        return attachFacilityRepository.findById(id);
    }

    @Override
    public AttachFacility save(AttachFacility attachFacility) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}

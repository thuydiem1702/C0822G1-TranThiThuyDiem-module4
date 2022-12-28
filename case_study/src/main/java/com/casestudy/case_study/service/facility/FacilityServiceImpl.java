package com.casestudy.case_study.service.facility;

import com.casestudy.case_study.model.facility.Facility;
import com.casestudy.case_study.repository.IFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class FacilityServiceImpl implements IFacilityService {

    @Autowired
    private IFacilityRepository facilityRepository;

    @Override
    public Iterable<Facility> findAll() {
        return facilityRepository.findAll();
    }

    @Override
    public Optional<Facility> findById(Integer id) {
        return facilityRepository.findById(id);
    }

    @Override
    public Facility save(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Page<Facility> findAll(String searchName, String searchRentType,
                                  String searchFacilityType, Pageable pageable) {
        return facilityRepository.findAll(searchName, searchRentType, searchFacilityType, pageable);
    }
}

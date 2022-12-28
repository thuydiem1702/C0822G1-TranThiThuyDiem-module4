package com.casestudy.case_study.service.facility;

import com.casestudy.case_study.model.facility.Facility;
import com.casestudy.case_study.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFacilityService extends IGeneralService<Facility> {
    Page<Facility> findAll(String searchName, String searchRentType,
                           String searchFacilityType, Pageable pageable);
}

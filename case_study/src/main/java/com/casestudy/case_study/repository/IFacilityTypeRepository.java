package com.casestudy.case_study.repository;

import com.casestudy.case_study.model.facility.FacilityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityTypeRepository extends JpaRepository<FacilityType,Integer> {
}

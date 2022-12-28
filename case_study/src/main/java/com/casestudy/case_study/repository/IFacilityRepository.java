package com.casestudy.case_study.repository;

import com.casestudy.case_study.model.facility.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IFacilityRepository extends JpaRepository<Facility, Integer> {
    @Query(
            value = "select * from facility where status=1",
            nativeQuery = true
    )
    List<Facility> findAll();

    @Query(
            value = "select * from facility where name like %:searchName% " +
                    "and rent_type_id like %:searchRentType% " +
                    "and facility_type_id like %:searchFacilityType% " +
                    "and status = 1",
            nativeQuery = true

    )
    Page<Facility> findAll(@Param("searchName") String searchName,
                           @Param("searchRentType") String searchRentType,
                           @Param("searchFacilityType") String searchFacilityType,
                           Pageable pageable);


    @Query(
            value = "select * from facility where status = 1 and id = :id",
            nativeQuery = true
    )
    Optional<Facility> findById(@Param("id") Integer id);
}

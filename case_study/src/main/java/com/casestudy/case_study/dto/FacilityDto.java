package com.casestudy.case_study.dto;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;

public class FacilityDto implements Validator {

    private Integer id;

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Area is required!")
    private String area;

    @NotEmpty(message = "Cost is required!")
    private String cost;

    @NotEmpty(message = "Max people is required!")
    private String maxPeople;

    private String standardRoom;

    private String descriptionOtherConvenience;

    private String poolArea;

    private String numberOfFloors;

    private String facilityFree;

    private String status;

    private String facilityType;

    private String rentType;

    public FacilityDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getStandardRoom() {
        return standardRoom;
    }

    public void setStandardRoom(String standardRoom) {
        this.standardRoom = standardRoom;
    }

    public String getDescriptionOtherConvenience() {
        return descriptionOtherConvenience;
    }

    public void setDescriptionOtherConvenience(String descriptionOtherConvenience) {
        this.descriptionOtherConvenience = descriptionOtherConvenience;
    }

    public String getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(String poolArea) {
        this.poolArea = poolArea;
    }

    public String getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getFacilityFree() {
        return facilityFree;
    }

    public void setFacilityFree(String facilityFree) {
        this.facilityFree = facilityFree;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getRentType() {
        return rentType;
    }

    public void setRentType(String rentType) {
        this.rentType = rentType;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        FacilityDto facility = (FacilityDto) target;

        String area = facility.getArea();
        String name = facility.getName();
        String cost = facility.getCost();
        String maxPeople = facility.getMaxPeople();
        String rentType = facility.getRentType();
        String facilityType = facility.getFacilityType();

        if (!name.equals("")) {
            if (!name.matches("^[A-Za-z0-9]*(?: [A-Z][a-z]*)*$")) {
                errors.rejectValue("name", "facilityName.matches", "error!");
            }
        }

        if (!area.equals("")) {
            Double areaValidate = Double.valueOf(facility.getArea());

            if (areaValidate <= 0) {
                errors.rejectValue("area", "area.matches", "error!");
            }

        }

        if (!cost.equals("")) {
            Double costValidate = Double.valueOf(facility.getCost());

            if (costValidate <= 0) {
                errors.rejectValue("cost", "cost.matches", "error!");
            }

        }

        if (!maxPeople.equals("")) {
            Integer maxPeopleValidate = Integer.valueOf(facility.getMaxPeople());

            if (maxPeopleValidate <= 0) {
                errors.rejectValue("maxPeople", "maxPeople.matches", "error!");
            }

        }

        if (rentType.equals("-1")) {
            errors.rejectValue("rentType", "rentType.matches", "error!");
        }

        if (facilityType.equals("-1")) {
            errors.rejectValue("facilityType", "facilityType.matches", "error!");
        }

        if (facility.getFacilityType().equals("1")) {
            String standardRoom = facility.getStandardRoom();
            String descriptionOtherConvenience = facility.getDescriptionOtherConvenience();
            String poolArea = facility.getPoolArea();
            String numberOfFloors = facility.getNumberOfFloors();


            if (standardRoom == null || standardRoom.trim().equals("")) {
                errors.rejectValue("standardRoom", "standardRoom.matches", "error!");
            }

            if (descriptionOtherConvenience == null || descriptionOtherConvenience.trim().equals("")) {
                errors.rejectValue("descriptionOtherConvenience",
                        "descriptionOtherConvenience.matches", "error!");
            }

            if (poolArea == null || poolArea.trim().equals("")) {

                errors.rejectValue("poolArea", "poolAreaNotEmpty.matches", "error!");

            } else {
                Double poolAreaValidate = Double.valueOf(facility.getPoolArea());

                if (poolAreaValidate <= 0) {
                    errors.rejectValue("poolArea", "poolArea.matches", "error!");
                }

            }

            if (numberOfFloors == null || numberOfFloors.trim().equals("")) {

                errors.rejectValue("numberOfFloors", "numberOfFloorsNotEmpty.matches", "error!");

            } else {
                Integer numberOfFloorsValidate = Integer.valueOf(facility.getNumberOfFloors());

                if (numberOfFloorsValidate <= 0) {
                    errors.rejectValue("numberOfFloors", "numberOfFloors.matches", "error!");
                }

            }

        }

        if (facility.getFacilityType().equals("2")) {
            String standardRoom = facility.getStandardRoom();
            String descriptionOtherConvenience = facility.getDescriptionOtherConvenience();
            String numberOfFloors = facility.getNumberOfFloors();

            if (standardRoom == null || standardRoom.trim().equals("")) {
                errors.rejectValue("standardRoom", "standardRoom.matches", "error!");
            }

            if (descriptionOtherConvenience == null || descriptionOtherConvenience.trim().equals("")) {
                errors.rejectValue("descriptionOtherConvenience",
                        "descriptionOtherConvenience.matches", "error!");
            }

            if (numberOfFloors == null || numberOfFloors.trim().equals("")) {

                errors.rejectValue("numberOfFloors", "numberOfFloorsNotEmpty.matches", "error!");

            } else {
                Integer numberOfFloorsValidate = Integer.valueOf(facility.getNumberOfFloors());

                if (numberOfFloorsValidate <= 0) {
                    errors.rejectValue("numberOfFloors", "numberOfFloors.matches", "error!");
                }

            }

        }

        if (facility.getFacilityType().equals("3")) {
            String facilityFreeValidate = facility.getFacilityFree();

            if (facilityFreeValidate == null || facilityFreeValidate.trim().equals("")) {
                errors.rejectValue("facilityFree", "facilityFree.matches", "error!");
            }

        }

    }
}
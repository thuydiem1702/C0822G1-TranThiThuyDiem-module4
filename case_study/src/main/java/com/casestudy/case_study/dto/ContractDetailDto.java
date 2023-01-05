package com.casestudy.case_study.dto;

public class ContractDetailDto {

    private Integer id;
    private String attachFacilityName;
    private String attachFacilityUnit;
    private Integer quantity;
    private Integer contractId;

    public ContractDetailDto() {
    }

    public ContractDetailDto(Integer id, String attachFacilityName, String attachFacilityUnit, Integer quantity) {
        this.id = id;
        this.attachFacilityName = attachFacilityName;
        this.attachFacilityUnit = attachFacilityUnit;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttachFacilityName() {
        return attachFacilityName;
    }

    public void setAttachFacilityName(String attachFacilityName) {
        this.attachFacilityName = attachFacilityName;
    }

    public String getAttachFacilityUnit() {
        return attachFacilityUnit;
    }

    public void setAttachFacilityUnit(String attachFacilityUnit) {
        this.attachFacilityUnit = attachFacilityUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }
}

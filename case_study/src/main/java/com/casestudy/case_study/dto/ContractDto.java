package com.casestudy.case_study.dto;

public class ContractDto {

    private Integer id;

    private Double deposit;

    private String startDate;
    private String endDate;
    private String customerName;
    private String facilityName;
    private String employeeName;
    private String totalMoney;
    private String contractDetailsString;

    public ContractDto() {
    }

    public ContractDto(Integer id, Double deposit, String startDate,
                       String endDate, String customerName, String facilityName, String employeeName) {
        this.id = id;
        this.deposit = deposit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customerName = customerName;
        this.facilityName = facilityName;
        this.employeeName = employeeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getContractDetailsString() {
        return contractDetailsString;
    }

    public void setContractDetailsString(String contractDetailsString) {
        this.contractDetailsString = contractDetailsString;
    }
}

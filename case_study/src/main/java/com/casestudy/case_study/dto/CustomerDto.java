package com.casestudy.case_study.dto;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotEmpty;

public class CustomerDto implements Validator {


    private Integer id;

    @NotEmpty(message = "Name is required!")
    private String name;

    @NotEmpty(message = "Birthday is required!")
    private String dateOfBirth;

    private String gender;

    @NotEmpty(message = "Id card is required!")
    private String idCard;

    @NotEmpty(message = "Phone number is required!")
    private String phoneNumber;

    @NotEmpty(message = "Email is required!")
    private String email;

    @NotEmpty(message = "Address is required!")
    private String address;

    private String customerType;

    public CustomerDto() {
    }

    public CustomerDto(Integer id, String name,
                       String dateOfBirth, String gender, String idCard,
                       String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.idCard = idCard;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDto customer = (CustomerDto) target;

        String name = customer.getName();
        String phoneNumber = customer.getPhoneNumber();
        String idCard = customer.getIdCard();
        String email = customer.getEmail();
        String gender = customer.getGender();
        String customerType = customer.getCustomerType();


        if (!name.equals("")) {
            if (!name.matches("^[A-Z][a-z]*(?: [A-Z][a-z]*)*$")) {
                errors.rejectValue("name", "customerName.matches", "error!");
            }
        }

        if (!phoneNumber.equals("")) {
            if (!phoneNumber.matches("^([(]84[)][+]90|[(]84[)][+]91|090|091)[0-9]{7}$")) {
                errors.rejectValue("phoneNumber", "customerPhone.matches", "error!");
            }
        }

        if (!idCard.equals("")) {
            if (!idCard.matches("^([0-9]{9}|[0-9]{12})$")) {
                errors.rejectValue("idCard", "idCard.matches", "error!");
            }
        }

        if (!email.equals("")) {
            if (!email.matches("^[a-zA-Z0-9]+[@]{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z0-9]+$")) {
                errors.rejectValue("email", "email.matches", "error!");
            }
        }

        if (gender.equals("-1")) {
            errors.rejectValue("gender", "gender.matches", "error!");
        }

        if (customerType.equals("-1")) {
            errors.rejectValue("customerType", "customerType.matches", "error!");
        }
    }
}
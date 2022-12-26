package com.casestudy.case_study.controller.customer;

import com.casestudy.case_study.dto.CustomerDto;
import com.casestudy.case_study.model.customer.Customer;
import com.casestudy.case_study.model.customer.CustomerType;
import com.casestudy.case_study.service.customer.ICustomerService;
import com.casestudy.case_study.service.customer_type.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerTypeService customerTypeService;

    @ModelAttribute("customerTypeList")
    public List<CustomerType> customerTypeList () {
        return (List<CustomerType>) customerTypeService.findAll();
    }

    @GetMapping("/list")
    public String searchCustomer(
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchAddress,
            @RequestParam(required = false) String searchCustomerType,
            @PageableDefault(value = 5) Pageable pageable,
            Model model) {

        if (searchAddress == null) {
            searchAddress = "";
        }

        if (searchName == null) {
            searchName = "";
        }

        if (searchCustomerType == null) {
            searchCustomerType = "";
        }

        Page<Customer> customersFound = customerService.search(searchName, searchAddress,
                searchCustomerType, pageable);
        List<CustomerDto> customerDtosFound = new ArrayList<>();


        for (Customer x : customersFound) {
            CustomerDto customerDto = new CustomerDto();
            BeanUtils.copyProperties(x, customerDto);

            if (x.getGender() == 1) {
                customerDto.setGender("Nam");
            } else {
                customerDto.setGender("Nữ");
            }

            customerDto.setCustomerType(x.getCustomerType().getName());
            customerDtosFound.add(customerDto);
        }

        Page<CustomerDto> customerDtosFoundPage = new PageImpl<>(customerDtosFound,
                pageable, customersFound.getTotalElements());

        model.addAttribute("customerList", customerDtosFoundPage);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchAddress", searchAddress);
        model.addAttribute("searchCustomerType", searchCustomerType);

        return "/customer/list/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        model.addAttribute("newCustomer", new CustomerDto());

        return "/customer/create";
    }

    @PostMapping("/create")
    public String createCustomer(@Validated @ModelAttribute("newCustomer") CustomerDto newCustomer,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        new CustomerDto().validate(newCustomer, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/customer/create";
        }

        Customer customerATBC = new Customer();
        Optional<CustomerType> customerType = customerTypeService.findById(Integer.valueOf(newCustomer.getCustomerType()));

        BeanUtils.copyProperties(newCustomer, customerATBC);
        customerATBC.setStatus(1);
        customerATBC.setGender(Integer.valueOf(newCustomer.getGender()));

        if (customerType.isPresent()) {
            customerATBC.setCustomerType(customerType.get());
        }

        customerService.save(customerATBC);

        redirectAttributes.addFlashAttribute("messSuccess", "New customer added successfully!");

        return "redirect:/customer/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id,
                               Model model) {

        CustomerDto editedCustomerDto = new CustomerDto();
        Optional<Customer> customerATBE = customerService.findById(Integer.valueOf(id));

        if (!customerATBE.isPresent()) {
            model.addAttribute("messFailure", "Customer not found!");
        }

        BeanUtils.copyProperties(customerATBE.get(), editedCustomerDto);
        editedCustomerDto.setCustomerType(String.valueOf(customerATBE.get().getCustomerType().getId()));

        if (customerATBE.get().getGender() == 1) {
            editedCustomerDto.setGender("1");
        } else {
            editedCustomerDto.setGender("0");
        }

        model.addAttribute("editedCustomerDto", editedCustomerDto);

        return "/customer/edit";
    }

    @PostMapping("/edit")
    public String editCustomer(@ModelAttribute CustomerDto editedCustomerDto,
                               RedirectAttributes redirectAttributes) {

        Customer editedCustomer = new Customer();
        Optional<CustomerType> customerType = customerTypeService.findById(Integer.valueOf(editedCustomerDto.getCustomerType()));

        BeanUtils.copyProperties(editedCustomerDto, editedCustomer);
        editedCustomer.setGender(Integer.valueOf(editedCustomerDto.getGender()));

        if (customerType.isPresent()) {
            editedCustomer.setCustomerType(customerType.get());
        }

        customerService.save(editedCustomer);

        redirectAttributes.addFlashAttribute("messSuccess", "Customer edited successfully!");

        return "redirect:/customer/list";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam String customerDeleteId,
                                 RedirectAttributes redirectAttributes) {

        Optional<Customer> customerATBD = customerService.findById(Integer.valueOf(customerDeleteId));

        if (customerATBD.isPresent()) {
            Customer customerDelete = customerATBD.get();
            customerDelete.setStatus(0);
            customerService.save(customerDelete);
        }

        redirectAttributes.addFlashAttribute("messSuccess", "Customer deleted successfully");

        return "redirect:/customer/list";
    }
}











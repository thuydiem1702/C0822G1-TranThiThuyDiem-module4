package com.casestudy.case_study.controller.contract;

import com.casestudy.case_study.dto.ContractDetailDto;
import com.casestudy.case_study.dto.ContractDto;
import com.casestudy.case_study.model.contract.AttachFacility;
import com.casestudy.case_study.model.contract.Contract;
import com.casestudy.case_study.model.contract.ContractDetail;
import com.casestudy.case_study.model.customer.Customer;
import com.casestudy.case_study.model.employee.Employee;
import com.casestudy.case_study.model.facility.Facility;
import com.casestudy.case_study.service.attach_facility_service.IAttachFacilityService;
import com.casestudy.case_study.service.contract.IContractService;
import com.casestudy.case_study.service.contract_detail.IContractDetailService;
import com.casestudy.case_study.service.customer.ICustomerService;
import com.casestudy.case_study.service.employee.IEmployeeService;
import com.casestudy.case_study.service.facility.IFacilityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private IAttachFacilityService attachFacilityService;

    @Autowired
    private IContractDetailService contractDetailService;

    @ModelAttribute("customerList")
    public List<Customer> getCustomerList() {
        return (List<Customer>) customerService.findAll();
    }

    @ModelAttribute("employeeList")
    public List<Employee> getEmployeeList() {
        return (List<Employee>) employeeService.findAll();
    }

    @ModelAttribute("facilityList")
    public List<Facility> facilityList() {
        return (List<Facility>) facilityService.findAll();
    }

    @ModelAttribute("attachFacilityList")
    public List<AttachFacility> attachFacilityList() {
        return (List<AttachFacility>) attachFacilityService.findAll();
    }

    @GetMapping("/list")
    public String getContractList(
            Model model,
            @RequestParam(required = false) String customerNameSearch,
            @RequestParam(required = false) String facilityNameSearch,
            @RequestParam(required = false) String displayType,
            @PageableDefault(value = 4) Pageable pageable) {

        Page<Contract> contractList;

        if (displayType == null) {
            displayType = String.valueOf(0);
        }

        if (customerNameSearch == null) {
            customerNameSearch = "";
        }

        if (facilityNameSearch == null) {
            facilityNameSearch = "";
        }

        if (displayType.equals("0")) {
            contractList = contractService.findAll(customerNameSearch, facilityNameSearch, pageable);
        } else {
            contractList = contractService.findAllCurrent(customerNameSearch, facilityNameSearch, pageable);
        }

        List<ContractDto> contractDtoList = new ArrayList<>();

        for (Contract x : contractList) {
            ContractDto contractDto = new ContractDto();
            BeanUtils.copyProperties(x, contractDto);

            contractDto.setCustomerName(x.getCustomer().getName());
            contractDto.setFacilityName(x.getFacility().getName());
            contractDto.setEmployeeName(x.getEmployee().getName());
            contractDto.setTotalMoney(contractService.getTotalMoney(x.getId()));

            contractDtoList.add(contractDto);
        }

        Page<ContractDto> contractDtoPage = new PageImpl<>(contractDtoList, pageable, contractList.getTotalElements());

        model.addAttribute("facilityNameSearch", facilityNameSearch);
        model.addAttribute("customerNameSearch", customerNameSearch);
        model.addAttribute("displayType", displayType);
        model.addAttribute("contractList", contractDtoPage);
        model.addAttribute("newContractDto", new ContractDto());
        model.addAttribute("newContractDetailDto", new ContractDetailDto());

        return "/contract/list";
    }

    @PostMapping("/create")
    public String createContract(@ModelAttribute ContractDto newContractDto,
                                 RedirectAttributes redirectAttributes) {

        Contract contractATBC = new Contract();

        BeanUtils.copyProperties(newContractDto, contractATBC);

        Optional<Customer> customerContract = customerService.findById(Integer.valueOf(newContractDto.getCustomerName()));
        Optional<Employee> employeeContract = employeeService.findById(Integer.valueOf(newContractDto.getEmployeeName()));
        Optional<Facility> facilityContract = facilityService.findById(Integer.valueOf(newContractDto.getFacilityName()));

        contractATBC.setCustomer(customerContract.get());
        contractATBC.setEmployee(employeeContract.get());
        contractATBC.setFacility(facilityContract.get());
        contractATBC.setStatus(1);

        contractService.save(contractATBC);

        if (!newContractDto.getContractDetailsString().equals("")) {
            String[] contractDetails = newContractDto.getContractDetailsString().split("");

            for (int i = 0; i < contractDetails.length; i = i + 2) {
                ContractDetail contractDetailATBC = new ContractDetail();
                contractDetailATBC.setQuantity(Integer.valueOf(contractDetails[i]));

                Optional<AttachFacility> attachFacilityATBC = attachFacilityService.findById(
                        Integer.valueOf(contractDetails[i + 1]));

                contractDetailATBC.setAttachFacility(attachFacilityATBC.get());

                contractDetailATBC.setContract(contractATBC);

                contractDetailATBC.setStatus(1);

                contractDetailService.save(contractDetailATBC);
            }
        }

        redirectAttributes.addFlashAttribute("messSuccess", "New contract added successfully!");

        return "redirect:/contract/list";
    }
}

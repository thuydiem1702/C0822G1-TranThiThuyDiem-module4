package com.example.case_study___.controller;

import com.example.case_study___.dto.CustomerDto;
import com.example.case_study___.model.customer.Customer;
import com.example.case_study___.service.customer.ICustomerService;
import com.example.case_study___.service.customer.ICustomerTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @Autowired
    private ICustomerTypeService iCustomerTypeService;

    @GetMapping("")
    public String showList(@PageableDefault(value = 5) Pageable pageable,
                           @RequestParam(value = "nameSearch", defaultValue = "") String nameSearch,
                           @RequestParam(value = "emailSearch", defaultValue = "") String emailSearch,
                           Model model) {

        model.addAttribute("customerList", iCustomerService.searchCustomer(nameSearch, emailSearch
                , pageable));
        model.addAttribute("customerTypeList", iCustomerTypeService.findAll());
        model.addAttribute("nameSearch", nameSearch);
        model.addAttribute("addressSearch", emailSearch);

        return "customer/list";
    }

    @GetMapping("/create")
    public String createCus(Model model) {
        model.addAttribute("customerDto", new CustomerDto());
        model.addAttribute("customerTypeList", iCustomerTypeService.findAll());

        return "customer/create";
    }

    @PostMapping("/add")
    public String saveCus(@ModelAttribute @Validated CustomerDto customerDto, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        new CustomerDto().validate(customerDto, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("customerTypeList", iCustomerTypeService.findAll());

            return "customer/create";
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        iCustomerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Thêm mới khách hàng thành công!");

        return "redirect:/customer";
    }

    @GetMapping("/edit/{id}")
    public String editCus(@PathVariable Integer id, Model model) {
        Customer customer = iCustomerService.findById(id).get();

        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(customer, customerDto);

        model.addAttribute("customerDto", customerDto);
        model.addAttribute("customerTypeList", iCustomerTypeService.findAll());

        return "customer/edit";
    }

    @PostMapping("/update")
    public String updateCus(@ModelAttribute @Validated CustomerDto customerDto, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {
        new CustomerDto().validate(customerDto, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("customerTypeList", iCustomerTypeService.findAll());

            return "customer/edit";
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        iCustomerService.update(customer);
        redirectAttributes.addFlashAttribute("message", "Chỉnh sửa khách hàng thành công!");

        return "redirect:/customer";
    }

    @GetMapping("/delete")
    public String deleteCus(@RequestParam(value = "idDelete") Integer id, RedirectAttributes redirectAttributes) {
        iCustomerService.deleteLogical(id);
        redirectAttributes.addFlashAttribute("message", "Xóa khách hàng  [" +
                iCustomerService.findById(id).get().getCustomerName() + "]  thành công.");

        return "redirect:/customer";
    }
}

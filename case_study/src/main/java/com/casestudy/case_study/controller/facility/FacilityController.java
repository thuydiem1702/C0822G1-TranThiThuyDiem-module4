package com.casestudy.case_study.controller.facility;

import com.casestudy.case_study.dto.FacilityDto;
import com.casestudy.case_study.model.facility.Facility;
import com.casestudy.case_study.model.facility.FacilityType;
import com.casestudy.case_study.model.facility.RentType;
import com.casestudy.case_study.service.facility.IFacilityService;
import com.casestudy.case_study.service.facility_type.IFacilityTypeService;
import com.casestudy.case_study.service.rent_type.IRentTypeService;
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
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    private IFacilityService facilityService;

    @Autowired
    private IFacilityTypeService facilityTypeService;

    @Autowired
    private IRentTypeService rentTypeService;

    @GetMapping("/list")
    public String showFacilityList(
            Model model,
            @RequestParam(required = false) String searchName,
            @RequestParam(required = false) String searchRentType,
            @RequestParam(required = false) String searchFacilityType,
            @PageableDefault(value = 3) Pageable pageable
    ) {
        if (searchName == null) {
            searchName = "";
        }
        if (searchRentType == null) {
            searchRentType = "";
        }
        if (searchFacilityType == null) {
            searchFacilityType = "";
        }
        Page<Facility> facilityList = facilityService.findAll(searchName, searchRentType,
                searchFacilityType,pageable);
        List<FacilityType> facilityTypeList = (List<FacilityType>) facilityTypeService.findAll();
        List<RentType> rentTypeList = (List<RentType>) rentTypeService.findAll();
        List<FacilityDto> facilityDtoList = new ArrayList<>();
        for (Facility x : facilityList) {
            FacilityDto facilityDto = new FacilityDto();
            BeanUtils.copyProperties(x, facilityDto);

            facilityDto.setFacilityType(x.getFacilityType().getName());
            facilityDto.setRentType(x.getRentType().getName());

            facilityDtoList.add(facilityDto);
        }

        Page<FacilityDto> facilityDtoPage = new PageImpl<>(facilityDtoList,
                pageable, facilityList.getTotalElements());

        model.addAttribute("facilityList", facilityDtoPage);
        model.addAttribute("rentTypeList", rentTypeList);
        model.addAttribute("facilityTypeList", facilityTypeList);
        model.addAttribute("searchName", searchName);
        model.addAttribute("searchRentType", searchRentType);
        model.addAttribute("searchFacilityType", searchFacilityType);

        return "facility/list/list";
    }

    @GetMapping("/view/{id}")
    public String showFacilityView(@PathVariable String id, Model model) {

        Optional<Facility> viewedFacility = facilityService.findById(Integer.valueOf(id));
        FacilityDto viewedFacilityDto = new FacilityDto();

        if (viewedFacility.isPresent()) {
            BeanUtils.copyProperties(viewedFacility.get(), viewedFacilityDto);

            viewedFacilityDto.setRentType(viewedFacility.get().getRentType().getName());
            viewedFacilityDto.setFacilityType(viewedFacility.get().getFacilityType().getName());
        }

        model.addAttribute("viewedFacility", viewedFacilityDto);

        return "/facility/view/view";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {

        model.addAttribute("newFacility", new FacilityDto());

        return "/facility/create";
    }

    @PostMapping("/create")
    public String createFacility (@Validated @ModelAttribute("newFacility") FacilityDto newFacility,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {

        new FacilityDto().validate(newFacility, bindingResult);

        if (bindingResult.hasErrors()) {
            if (newFacility.getFacilityType().equals("1")) {
                model.addAttribute("flag", "1");
            }

            if (newFacility.getFacilityType().equals("2")) {
                model.addAttribute("flag", "2");
            }

            if (newFacility.getFacilityType().equals("3")) {
                model.addAttribute("flag", "3");
            }
            return "facility/create";
        }


        Optional<RentType> rentType = rentTypeService.findById(Integer.valueOf(newFacility.getRentType()));
        Optional<FacilityType> facilityType = facilityTypeService.findById(Integer.valueOf(newFacility.getFacilityType()));

        Facility facilityATBC = new Facility();

        BeanUtils.copyProperties(newFacility, facilityATBC);
        facilityATBC.setFacilityType(facilityType.get());
        facilityATBC.setRentType(rentType.get());
        facilityATBC.setStatus(1);

        facilityService.save(facilityATBC);

        redirectAttributes.addFlashAttribute("messSuccess", "New facility created successfully!");

        return "redirect:/facility/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id,
                               Model model) {

        FacilityDto editedFacilityDto = new FacilityDto();
        Optional<Facility> facilityATBE = facilityService.findById(Integer.valueOf(id));
        List<FacilityType> facilityTypeList = (List<FacilityType>) facilityTypeService.findAll();
        List<RentType> rentTypeList = (List<RentType>) rentTypeService.findAll();

        if (!facilityATBE.isPresent()) {
            model.addAttribute("messFailure", "Customer not found!");
        }

        BeanUtils.copyProperties(facilityATBE.get(), editedFacilityDto);
        editedFacilityDto.setFacilityType(String.valueOf(facilityATBE.get().getFacilityType().getId()));
        editedFacilityDto.setRentType(String.valueOf(facilityATBE.get().getRentType().getId()));

        model.addAttribute("editedFacilityDto", editedFacilityDto);
        model.addAttribute("facilityTypeList", facilityTypeList);
        model.addAttribute("rentTypeList", rentTypeList);

        return "/facility/edit";
    }

    @PostMapping("/edit")
    public String editFacility(@ModelAttribute FacilityDto editedFacilityDto,
                               RedirectAttributes redirectAttributes) {

        Facility editedFacility = new Facility();
        Optional<RentType> rentType = rentTypeService.findById(Integer.valueOf(editedFacilityDto.getRentType()));
        Optional<FacilityType> facilityType = facilityTypeService.findById(Integer.valueOf(editedFacilityDto.getFacilityType()));

        BeanUtils.copyProperties(editedFacilityDto, editedFacility);

        if (facilityType.isPresent()) {
            editedFacility.setFacilityType(facilityType.get());
        }

        if (rentType.isPresent()) {
            editedFacility.setRentType(rentType.get());
        }

        facilityService.save(editedFacility);

        redirectAttributes.addFlashAttribute("messSuccess", "Facility edited successfully!");

        return "redirect:/facility/list";
    }

    @PostMapping("/delete")
    public String deleteFacility(@RequestParam String facilityDeleteId,
                                 RedirectAttributes redirectAttributes) {

        Optional<Facility> facilityATBD = facilityService.findById(Integer.valueOf(facilityDeleteId));

        if (facilityATBD.isPresent()) {
            Facility facilityDelete = facilityATBD.get();
            facilityDelete.setStatus(0);
            facilityService.save(facilityDelete);
        }

        redirectAttributes.addFlashAttribute("messSuccess", "Facility deleted successfully");

        return "redirect:/facility/list";
    }

}

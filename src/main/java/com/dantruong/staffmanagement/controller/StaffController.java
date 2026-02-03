package com.dantruong.staffmanagement.controller;

import com.dantruong.staffmanagement.config.StaffReposi;
import com.dantruong.staffmanagement.entity.Staff;
import com.dantruong.staffmanagement.service.StaffService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class StaffController {
    private final StaffReposi staffReposi;
    private final StaffService staffService;

    public StaffController(StaffReposi staffReposi, StaffService staffService) {
        this.staffReposi = staffReposi;
        this.staffService = staffService;
    }

    @ModelAttribute("staff")
    public Staff staff(){
        return new Staff();
    }

    @GetMapping("/staff/application")
    public ModelAndView showForm(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @PostMapping("/create-staff")
    public ModelAndView addStaff(@ModelAttribute("staff") Staff staff, @RequestParam MultipartFile file) throws IOException {
                String fileName = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get("uploads", fileName);

        Files.write(fileNameAndPath, file.getBytes());

        staff.setPicture(fileName);

staffService.insertStaff(staff);
            return new ModelAndView ("redirect:/staffs");
    }


    @GetMapping("/staffs")
    public String viewStaff(Model model){
        model.addAttribute("listStaffs", staffReposi.findAll());
        return "view";
    }

    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id){
        staffService.delete(id);
        return "redirect:/staffs";
    }

    @PostMapping("/update")
    public String updateStaff(@ModelAttribute("staff") Staff staff) {
        Integer id = staff.getId();

        staffService.updateStaff(id, staff);

        return "redirect:/staffs";
    }
}

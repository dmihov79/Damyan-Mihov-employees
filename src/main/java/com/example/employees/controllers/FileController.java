package com.example.employees.controllers;

import com.example.employees.entities.Employee;
import com.example.employees.services.CSVFileService;
import com.example.employees.views.PairView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Controller()
public class FileController {
    @Autowired
    CSVFileService csvFileService;
    @GetMapping("/home")
    public String homePage() {
        return "index";
    }
    @PostMapping("/upload")
    public String uploadCSVFile(Model model, @RequestParam("file") MultipartFile file) {
        model.addAttribute("pairViews",csvFileService.getEmployeePairsForFile(file));
        return "pair_view_list";
    }
}

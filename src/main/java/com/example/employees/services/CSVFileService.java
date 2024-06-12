package com.example.employees.services;

import com.example.employees.entities.Employee;
import com.example.employees.views.PairView;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.*;

@Service
public class CSVFileService {

    private Employee getEmployeeFromCSVLine(String line){
        return new Employee();
    }

    public List<PairView> getEmployeePairsForFile(MultipartFile file) {
        return List.of(new PairView(new Employee(1, 1, new Date(), new Date()), new Employee(1, 1, new Date(), new Date()), 1, 1));
    }
}

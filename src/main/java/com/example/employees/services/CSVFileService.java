package com.example.employees.services;

import com.example.employees.entities.Employee;
import com.example.employees.views.PairView;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CSVFileService {
    @Autowired
    private DateUtils dateUtils;

    private Employee getEmployeeFromCSVLine(String line){
        return new Employee();
    }

    public List<PairView> getEmployeePairsForFile(MultipartFile file) throws IOException, ParseException {
        List<PairView> pairViews = new ArrayList<>();
        List<Employee> employees = getEmployeesFromCSVFile(file);
        Map<Integer, List<Employee>> projectEmployeeMap = createProjectEmployeeMap(employees);

        for(Integer projectID: projectEmployeeMap.keySet()) {
            if(projectEmployeeMap.get(projectID).size() > 1) {
                List<Employee> list = projectEmployeeMap.get(projectID);
                PairView pairView = createPairViewFromEmployees(list, projectID);
                pairViews.add(pairView);
            }
        }

        return pairViews;
    }

    private PairView createPairViewFromEmployees(List<Employee> list, int projectID) {
        Employee longestWorkingEmp1 = new Employee();
        Employee longestWorkingEmp2 = new Employee();
        long daysWorking1 = Integer.MIN_VALUE;
        long daysWorking2 = Integer.MIN_VALUE;
        for(Employee employee: list) {
            long daysWorking = getDifferenceDays(employee.getDateFrom(), employee.getDateTo());
            if(daysWorking > daysWorking1) {
                daysWorking2 = daysWorking1;
                longestWorkingEmp2 = longestWorkingEmp1;
                daysWorking1 = daysWorking;
                longestWorkingEmp1 = employee;
            } else if (daysWorking > daysWorking2 && daysWorking != daysWorking1) {
                daysWorking2 = daysWorking;
                longestWorkingEmp2 = employee;
            }
        }
        return new PairView(longestWorkingEmp1, longestWorkingEmp2, projectID, daysWorking1 + daysWorking2);
    }

    private long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    private Map<Integer, List<Employee>> createProjectEmployeeMap(List<Employee> employees) {
        Map<Integer, List<Employee>> result = new HashMap<>();

        for(Employee employee: employees) {
            if(result.get(employee.getProjectID()) != null){
                result.get(employee.getProjectID()).add(employee);
            }
            else {
                List<Employee> list = new ArrayList<>();
                list.add(employee);
                result.put(employee.getProjectID(), list);
            }
        }

        return result;
    }

    private List<Employee> getEmployeesFromCSVFile(MultipartFile file) throws IOException, ParseException {
        BufferedReader br;
        List<Employee> result = new ArrayList<>();
        String line;
        InputStream is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));

        while ((line = br.readLine()) != null) {
            //remove blank spaces
            line = line.replaceAll("\\s+","");
            int empID = Integer.valueOf(line.split(",")[0]);
            int projectID = Integer.valueOf(line.split(",")[1]);
            Date fromDate = dateUtils.parseDateStrictly(line.split(",")[2],new String[]{"yyyy/MM/dd", "dd/MM/yyyy","MM/dd/yyyy",
                                                                                              "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyy"});
            Date toDate;
            if("NULL".equals(line.split(",")[3]))
                toDate = new Date(System.currentTimeMillis());
            else
                toDate = dateUtils.parseDateStrictly(line.split(",")[3],new String[]{"yyyy/MM/dd", "dd/MM/yyyy","MM/dd/yyyy",
                                                                                            "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyy"});
            result.add(new Employee(empID, projectID, fromDate, toDate));
        }

        return result;
    }
}

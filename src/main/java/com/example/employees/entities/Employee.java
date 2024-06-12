package com.example.employees.entities;

import java.util.Date;

public class Employee {
    private int empID;
    private int projectID;
    private Date dateFrom;
    private Date dateTo;

    public Employee() {
    }

    public Employee(int empID, int projectID, Date dateFrom, Date dateTo) {
        this.empID = empID;
        this.projectID = projectID;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getEmpID() {
        return empID;
    }

    public int getProjectID() {
        return projectID;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}

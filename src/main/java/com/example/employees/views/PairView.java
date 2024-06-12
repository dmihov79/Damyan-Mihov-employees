package com.example.employees.views;

import com.example.employees.entities.Employee;

public class PairView {
    private Employee employee1;
    private Employee employee2;
    private int projectID;
    private int daysWorked;

    public PairView(Employee employee1, Employee employee2, int projectID, int daysWorked) {
        this.employee1 = employee1;
        this.employee2 = employee2;
        this.projectID = projectID;
        this.daysWorked = daysWorked;
    }

    public PairView() {
    }

    public Employee getEmployee1() {
        return employee1;
    }

    public Employee getEmployee2() {
        return employee2;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public void setEmployee1(Employee employee1) {
        this.employee1 = employee1;
    }

    public void setEmployee2(Employee employee2) {
        this.employee2 = employee2;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }
}

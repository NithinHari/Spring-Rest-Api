package com.dxc.cba.employeedemo.service;

import com.dxc.cba.employeedemo.model.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Employee addEmployee(Employee employeeRecord);
    public Employee updateEmployee(Employee employeeRecord);
    public void deleteById(Long id);
    public List<Employee> getFilteredEmployees(String designation, String department);
}

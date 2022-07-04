package com.dxc.cba.employeedemo.service;

import java.util.ArrayList;
import java.util.List;

import com.dxc.cba.employeedemo.exception.CustomEmployeeException;
import com.dxc.cba.employeedemo.model.Employee;
import com.dxc.cba.employeedemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // for used with classes that provide some business functionalities
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employee = new ArrayList<>();
        employeeRepository.findAll().forEach(employee::add);
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employeeRecord) {
        if (employeeRecord != null && employeeRecord.getEmployeeName() != null) {
            return employeeRepository.save(employeeRecord);
        } else {
            throw new CustomEmployeeException("Name cannot be null");
        }
    }

    @Override
    public Employee updateEmployee(Employee employeeRecord) {

        return employeeRepository.save(employeeRecord);
    }

    @Override
    public void deleteById(Long id) {

        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getFilteredEmployees(String designation, String department) {

        return employeeRepository.findEmployeesByCriteria(designation, department);
    }
}



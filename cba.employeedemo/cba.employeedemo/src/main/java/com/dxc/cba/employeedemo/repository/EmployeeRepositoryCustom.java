package com.dxc.cba.employeedemo.repository;


import java.util.List;

import com.dxc.cba.employeedemo.model.Employee;

public interface EmployeeRepositoryCustom {
    public List<Employee> findEmployeesByCriteria(String designation, String department);

}

package com.dxc.cba.employeedemo.repository;

import com.dxc.cba.employeedemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EmployeeRepository extends JpaRepository<Employee,Long>, EmployeeRepositoryCustom {

}



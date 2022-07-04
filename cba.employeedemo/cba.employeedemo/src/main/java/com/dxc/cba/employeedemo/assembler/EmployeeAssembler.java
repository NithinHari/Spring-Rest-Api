package com.dxc.cba.employeedemo.assembler;

import com.dxc.cba.employeedemo.model.Employee;
import org.springframework.stereotype.Component;

@Component
/**
 * @Component is an annotation that allows Spring to automatically detect our custom beans.
 *
 * In other words, without having to write any explicit code, Spring will:
 *
 * a.Scan our application for classes annotated with @Component
 * b.Instantiate them and inject any specified dependencies into them
 * c.Inject them wherever needed
 */
public class EmployeeAssembler {

    public Employee toEmployeeEntity(Employee employee){
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeName(employee.getEmployeeName());
        newEmployee.setDesignation(employee.getDesignation());
        newEmployee.setAddress(employee.getAddress());
        newEmployee.setDepartment(employee.getDepartment());
        return  newEmployee;
    }
}

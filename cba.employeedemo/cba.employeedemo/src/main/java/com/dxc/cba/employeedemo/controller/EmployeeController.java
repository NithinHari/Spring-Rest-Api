package com.dxc.cba.employeedemo.controller;


import com.dxc.cba.employeedemo.assembler.EmployeeAssembler;
import com.dxc.cba.employeedemo.exception.CustomEmployeeException;
import com.dxc.cba.employeedemo.exception.NoSuchEmployeeExistsException;
import com.dxc.cba.employeedemo.model.Employee;
import com.dxc.cba.employeedemo.repository.EmployeeRepository;
import com.dxc.cba.employeedemo.service.EmployeeService;
import com.dxc.cba.employeedemo.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @RestController: It is the combination of @Controller and @ResponseBody annotations.
 * The @RestController annotation is itself annotated with the @ResponseBody annotation.
 *
 * @RequestMapping: It is used to map the web requests. It has many optional elements like consumes, header, method, name, params,
 * path, produces, and value.
 *
 * ResponseEntity is a simple wrapper of HTTP response object;
 * it provides fine-grained control to specify HTTP status codes, HTTP headers and response body.
 *
 * @PathVariable: It is used to extract the values from the URI. It is most suitable for the RESTful web service,
 * where the URL contains a path variable. We can define multiple @PathVariable in a method.
 *
 * @RequestParam: It is used to extract the query parameters form the URL. It is also known as a query parameter. It is most suitable for web applications.
 * It can specify default values if the query parameter is not present in the URL.
 *
 * @RequestBody: It is used to bind HTTP request with an object in a method parameter.
 * Internally it uses HTTP MessageConverters to convert the body of the request.
 * When we annotate a method parameter with @RequestBody, the Spring framework binds the incoming HTTP request body to that parameter.
 *
 * @Autowired:
 * It is used to autowire spring bean on setter methods, instance variable, and constructor.
 * When we use @Autowired annotation, the spring container auto-wires the bean by matching data-type.
 *
 * @Bean Annotation is applied on a method to specify that it returns a bean to be managed by Spring context.
 * Spring Bean annotation is usually declared in Configuration classes methods.
 * In this case, bean methods may reference other @Bean methods in the same class by calling them directly
 */

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAssembler assembler;

//    @Bean
//    private EmployeeAssembler assembler(){
//        return new EmployeeAssembler();
//    };

    /**
     * Get all employees.
     *
     * @return
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        //try {
            return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    /**
     * Get the employee by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        //check if employee exist in database
        Employee empObj = getEmpRec(id);

        if (empObj != null) {
            return new ResponseEntity<>(empObj, HttpStatus.OK);
        } else {
            throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        // just an use case of using a component bean
        //Employee ex=assembler().toEmployeeEntity(employee);
        Employee ex1=assembler.toEmployeeEntity(employee);
        //System.out.println("Ass" + ex.getEmployeeName());
        System.out.println("Ass1" + ex1.getEmployeeName());
        if (employee.getEmployeeName() != null && !employee.getEmployeeName().equalsIgnoreCase(" ")) {
            System.out.println("Book Added");
            return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
        } else {
            throw new CustomEmployeeException("Employee Name cannot be null or empty");
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getFilteredEmployees(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String designation) {

        return new ResponseEntity<>(employeeService.getFilteredEmployees(designation, department), HttpStatus.OK);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") final Long id, @RequestBody final Employee employeeRecord) {
        Employee employee = getEmpRec(id);
        if (employee != null) {
            employeeRecord.setId(id);
            return new ResponseEntity<>(employeeService.updateEmployee(employeeRecord), HttpStatus.OK);
        } else {
            throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
        }

    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployeeById(@PathVariable("id") long id) {
            //check if employee exist in database
            Employee emp = getEmpRec(id);

            if (emp != null) {
                employeeService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                throw new NoSuchEmployeeExistsException("No Such Employee exists!!");
            }
    }

    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Method to get the employee record by id
     *
     * @param id
     * @return Employee
     */
    private Employee getEmpRec(long id) {
        Optional<Employee> empObj = employeeRepository.findById(id);

        if (empObj.isPresent()) {
            return empObj.get();
        }
        return null;
    }


}

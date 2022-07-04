package com.dxc.cba.employeedemo.model;
import javax.persistence.*;

/**
 * The @Entity annotation specifies that the class is an entity and is mapped to a database table.
 *
 * The @Table annotation specifies the name of the database table to be used for mapping.
 *
 * The @Id annotation specifies the primary key of an entity and the
 * @GeneratedValue provides for the specification of generation strategies for the values of primary keys.
 *
 *  @Column annotation is used for Adding the column the name in the table of a particular MySQL database.
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "employeeName")
    private String employeeName;
    @Column(name = "designation")
    private String designation;
    @Column(name = "address")
    private String address;
    @Column(name = "department")
    private String department;

    public Employee(Long id, String employeeName, String designation, String address, String department) {
        this.id = id;
        this.employeeName = employeeName;
        this.designation = designation;
        this.address = address;
        this.department = department;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

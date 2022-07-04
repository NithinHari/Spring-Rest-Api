package com.dxc.cba.employeedemo.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dxc.cba.employeedemo.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
/**
 * It is a class-level annotation. The repository is a DAOs (Data Access Object)
 * that access the database directly. The repository does all the operations related to the database.
 * if we want a @Repository interface to have a method with a custom implementation,
 * we can use composable repositories.
 * Eg: Since  JPA repository name is EmployeeRepository, the custom Interface name should be EmployeeRepositoryCustom (it should end with 'Custom')
 * and the implementation class name should be EmployeeRepositoryImpl (should end with Impl)
 */
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> findEmployeesByCriteria(String designation, String department) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();

        if (designation != null) {
            predicates.add(cb.like(employee.get("designation"), "%" + designation + "%"));
        }
        if (department != null) {
            predicates.add(cb.like(employee.get("department"), "%" + department + "%"));
        }

        Predicate predicateForGrade = cb.and(predicates.toArray(new Predicate[0]));
        cq.where(predicateForGrade);

        return entityManager.createQuery(cq).getResultList();
    }

}
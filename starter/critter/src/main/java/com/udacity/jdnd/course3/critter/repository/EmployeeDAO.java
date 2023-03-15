package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    Employee save(Employee s);
    List<Employee> findEmployeeById(Long employee_id);
}

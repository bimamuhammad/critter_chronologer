package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    public void saveEmployee(Employee employee){
        employeeDAO.save(employee);
    }

    public Employee getEmployee(Long employeeId){
        return employeeDAO.findEmployeeById(employeeId).get(0);
    }
}

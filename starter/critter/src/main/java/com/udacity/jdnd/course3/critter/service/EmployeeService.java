package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeDAO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;

    public Employee saveEmployee(Employee employee){
        return employeeDAO.save(employee);
    }

    public Employee getEmployee(Long employeeId){
        return employeeDAO.findEmployeeById(employeeId).get(0);
    }

    public List<Employee> findEmployeeBySkillAndDaysavailable(
            Set<EmployeeSkill> skills, LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        Set<DayOfWeek> dayOfWeekSet = new HashSet<>();
        dayOfWeekSet.add(dayOfWeek);

        List<Employee> employeeList =  employeeDAO.findEmployeeDistinctBySkillsInAndDaysAvailableIn(skills, dayOfWeekSet);
        return employeeList.stream().filter(x->x.getSkills().containsAll(skills)).collect(Collectors.toList());
    }
}

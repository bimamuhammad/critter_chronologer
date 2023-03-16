package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    Employee save(Employee s);
    List<Employee> findEmployeeById(Long employee_id);

    List<Employee> findEmployeeDistinctBySkillsInAndDaysAvailableIn(
            Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable
    );
//    SELECT skills, days_available FROM critter.skill join critter.days_available on skill.employee_id=days_available.employee_id;
}

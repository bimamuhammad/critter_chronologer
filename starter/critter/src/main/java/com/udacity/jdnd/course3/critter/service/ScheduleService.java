package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleDAO scheduleDAO;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    CustomerService customerService;

    public Schedule createSchedule(Schedule schedule) {
        return scheduleDAO.save(schedule);
    }

    
    public List<Schedule> getAllSchedules() {
        return scheduleDAO.findAllSchedules();
    }

    
    public List<Schedule> getScheduleFOrPet(Long petid) {
        return scheduleDAO.findScheduleByPetId(petid);
    }

    
    public List<Schedule> getScheduleForEmployee(Long employeeid) {
        List<Employee> employee = new ArrayList<>();
        employee.add(employeeService.getEmployee(employeeid));
        return scheduleDAO.findAllScheduleByEmployeeIn(employee);
    }

    
    public List<Schedule> getScheduleForCustomer(Long customerid) {
        return scheduleDAO.findAllScheduleByPetCustomerEquals(customerService.getCustomerById(customerid));
    }
}

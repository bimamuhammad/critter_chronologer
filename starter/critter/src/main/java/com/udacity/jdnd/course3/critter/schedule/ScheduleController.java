package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.User;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        List<Employee> employeeList = scheduleDTO.getEmployeeIds().stream().map(x->employeeService.getEmployee(x)).collect(Collectors.toList());
        List<Pet> petList = scheduleDTO.getPetIds().stream().map(x->petService.getPet(x)).collect(Collectors.toList());
        Set<EmployeeSkill> activities = scheduleDTO.getActivities();

        Schedule schedule = new Schedule();
        schedule.setPet(petList);
        schedule.setDate(scheduleDTO.getDate());
        schedule.setEmployee(employeeList);
        schedule.setActivities(activities);
        schedule = scheduleService.createSchedule(schedule);

        ScheduleDTO sd = new ScheduleDTO();
        BeanUtils.copyProperties(scheduleDTO, sd);
        sd.setId(schedule.getId());
        return sd;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return toDTOList(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return toDTOList(scheduleService.getScheduleFOrPet(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return toDTOList(scheduleService.getScheduleForEmployee(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return toDTOList(scheduleService.getScheduleForCustomer(customerId));
    }
    
    private List<ScheduleDTO> toDTOList(List<Schedule> scheduleList){
        return scheduleList.stream().map(x->{
            ScheduleDTO sd = new ScheduleDTO();
            BeanUtils.copyProperties(x, sd);
            sd.setEmployeeIds(x.getEmployee().stream().map(User::getId).collect(Collectors.toList()));
            sd.setPetIds(x.getPet().stream().map(Pet::getId).collect(Collectors.toList()));
            return sd;
        }).collect(Collectors.toList());
    }
}

package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ScheduleDAO extends JpaRepository<Schedule, Long> {
    Schedule save(Schedule schedule);

    @Query(value="SELECT * from Schedule s", nativeQuery = true)
    List<Schedule> findAllSchedules();

    List<Schedule> findScheduleByPetId(Long petid);

//    @Query(value="SELECT * from Schedule s WHERE s.employee_id=:employeeid", nativeQuery = true)
    List<Schedule> findAllScheduleByEmployeeIn(List<Employee> employee);

    List<Schedule> findAllScheduleByPetCustomerEquals(Customer customer);
}

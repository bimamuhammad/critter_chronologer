//package com.udacity.jdnd.course3.critter.repository;
//
//import com.udacity.jdnd.course3.critter.entity.Schedule;
//import com.udacity.jdnd.course3.critter.schedule.ScheduleDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Repository
//@Transactional
//public class ScheduleDAOImpl implements ScheduleDAO {
//    @Autowired
//    NamedParameterJdbcTemplate jdbcTemplate;
//
//
//    @Override
//    public Schedule createSchedule(Schedule schedule) {
//        return null;
//    }
//
//    @Override
//    public List<Schedule> getAllSchedules() {
//        String query = "SELECT * from schedule";
//        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Schedule.class));
//    }
//
//    @Override
//    public List<Schedule> getScheduleFOrPet(Long petid) {
//        return null;
//    }
//
//    @Override
//    public List<Schedule> getScheduleForEmployee(Long employeeid) {
//        return null;
//    }
//
//    @Override
//    public List<Schedule> getScheduleForCustomer(Long customerid) {
//        return null;
//    }
//}

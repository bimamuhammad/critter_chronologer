package com.udacity.jdnd.course3.critter.user;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerDAO;
import com.udacity.jdnd.course3.critter.repository.EmployeeDAO;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    PetService petService;

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.listAllCustomers().stream().map(
                x->new CustomerDTO(x.getId(), x.getName(), x.getPhoneNumber(), x.getNotes(), petService.getPetsByOwnerId(x.getId()).stream().map(
                        Pet::getId).collect(Collectors.toList()))
        ).collect(Collectors.toList());
    }


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customerService.saveCustomer(customer);
        CustomerDTO retCustomerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, retCustomerDTO);
        return retCustomerDTO;
    }

    @GetMapping("/customer/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable Long petId){
        Customer customer = customerService.getCustomerByPetId(petId);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee = employeeService.saveEmployee(employee);
        EmployeeDTO returnDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, returnDTO);
        return returnDTO;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return new EmployeeDTO(employeeId, employee.getName(), employee.getSkills(), employee.getDaysAvailable());
    }


    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employeeList = employeeService.
                findEmployeeBySkillAndDaysavailable(
                        employeeDTO.getSkills(), employeeDTO.getDate()
                );
        return employeeList.stream().map(x->{
            EmployeeDTO employeeDTO1 = new EmployeeDTO();
            BeanUtils.copyProperties(x, employeeDTO1);
            return employeeDTO1;
        }).collect(Collectors.toList());
    }



}

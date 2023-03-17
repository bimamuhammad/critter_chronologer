package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerDAO;
import com.udacity.jdnd.course3.critter.repository.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {
    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    PetDAO petDAO;

    public void saveCustomer(Customer customer){
        customerDAO.saveCustomer(customer);
    }

    public List<Customer> listAllCustomers(){
        return new ArrayList<>(customerDAO.listAllCustomers());
    }

    public Customer getCustomerById(long customerid){
        return customerDAO.findCustomer(customerid);
    }

    public Customer getCustomerByPetId(Long petId){
        Pet pet = petDAO.getPetByID(petId);
        Customer customer = this.getCustomerById(pet.getCustomer().getId());
        customer.setPets(petDAO.getPetsByOwnerID(customer.getId()));
        return customer;
    }

}

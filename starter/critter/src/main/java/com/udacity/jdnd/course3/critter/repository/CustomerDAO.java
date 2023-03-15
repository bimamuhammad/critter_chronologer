package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class CustomerDAO {
    @PersistenceContext
    EntityManager entityManager;

    public void saveCustomer(Customer customer){
        entityManager.persist(customer);
        entityManager.flush();
    }

    public Customer findCustomer(Long id){
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> listAllCustomers(){
        TypedQuery<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class);
        return customers.getResultList();
    }

}

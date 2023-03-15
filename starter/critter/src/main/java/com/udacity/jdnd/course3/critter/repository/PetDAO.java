package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.Pet;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;


@Repository
@Transactional
public class PetDAO {
    @PersistenceContext
    EntityManager entityManager;

    public void savePet(Pet pet){
        entityManager.persist(pet);
    }
    public void mergePet(Pet pet){
        entityManager.merge(pet);
    }

    public List<Pet> getAllPets(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pet> criteriaQuery = criteriaBuilder.createQuery(Pet.class);
        Root<Pet> root = criteriaQuery.from(Pet.class);

        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Pet getPetByID(Long petId){
        TypedQuery<Pet> petQuery = entityManager.createQuery("SELECT p from Pet p WHERE p.id = :pid", Pet.class);
        petQuery.setParameter("pid", petId);
        return petQuery.getSingleResult();
    }

    public List<Pet> getPetsByOwnerID(Long ownerId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();;
        CriteriaQuery<Pet> petQuery = criteriaBuilder.createQuery( Pet.class);
        Root<Pet> root = petQuery.from(Pet.class);

        petQuery.select(root).where(criteriaBuilder.equal(root.get("customer").get("id"), ownerId));
        return entityManager.createQuery(petQuery).getResultList();


    }


}

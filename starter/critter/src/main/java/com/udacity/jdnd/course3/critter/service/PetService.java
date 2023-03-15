package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    PetDAO petDAO;

    public void savePet(Pet pet){
        if(pet.getId()!=0){
            petDAO.mergePet(pet);
        }else {
            petDAO.savePet(pet);
        }
    }

    public List<Pet> getAllPets(){
        return petDAO.getAllPets();
    }

    public Pet getPet(Long id){
        return petDAO.getPetByID(id);
    }

    public List<Pet> getPetsByOwnerId(Long ownerId){
        return petDAO.getPetsByOwnerID(ownerId);
    }
}

package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;
    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDto) {
        Pet pet = new Pet(0L, petDto.getType(), petDto.getName(), petDto.getBirthDate(), petDto.getNotes());
        Customer customer = customerService.getCustomerById(petDto.getOwnerId());
        pet.setCustomer(customer);
        petService.savePet(pet);
        PetDTO newPetDTO = new PetDTO();
        BeanUtils.copyProperties(pet, newPetDTO);
        return newPetDTO;

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPet(petId);
        return new PetDTO(
                pet.getId(),
                pet.getType(),
                pet.getName(),
                pet.getCustomer().getId(),
                pet.getBirthDate(),
                pet.getNotes()
        );
    }

    @GetMapping
    public List<PetDTO> getPets(){

        List<Pet> petList = petService.getAllPets();
        return petList.stream().map(
                x->new PetDTO(x.getId(), x.getType(), x.getName(), x.getCustomer().getId(), x.getBirthDate(), x.getNotes())
        ).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> petList = petService.getPetsByOwnerId(ownerId);
        return petList.stream().map(
                pet -> new PetDTO(pet.getId(), pet.getType(), pet.getName(), ownerId, pet.getBirthDate(), pet.getNotes())
        ).collect(Collectors.toList());
    }
}

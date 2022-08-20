package com.globits.sample.rest;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.sample.domain.Animal;
import com.globits.sample.service.AnimalService;

@RestController
@RequestMapping("/public/animal")
public class RestAnimalPublicController {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private AnimalService animalService;

	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public Page<Animal> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<Animal> page = animalService.getList(pageIndex, pageSize);
		return page;
	}
	@RequestMapping(method = RequestMethod.GET)
	public Page<Animal> getListByParam(@RequestParam(name="page") int page, @RequestParam(name="size") int size) {
		Page<Animal> pageInfo = animalService.getList(page, size);
		return pageInfo;
	}

	
	@RequestMapping(value = "/{animalId}", method = RequestMethod.GET)
	public Animal getAnimal(@PathVariable("animalId") String animalId) {
		Animal animal = animalService.findById(UUID.fromString(animalId));
		// building = new Building(building);
		return animal;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Animal saveAnimal(@RequestBody Animal animal) {
		return animalService.save(animal);
	}

	@RequestMapping(value = "/{animalId}", method = RequestMethod.PUT)
	public Animal updateAnimal(@RequestBody Animal Animal, @PathVariable("animalId") Long AnimalId) {
		return animalService.save(Animal);
	}

	@RequestMapping(value = "/{animalId}", method = RequestMethod.DELETE)
	public Animal removeAnimal(@PathVariable("animalId") String animalId) {
		Animal Animal = animalService.delete(UUID.fromString(animalId));
		return Animal;
	}
}

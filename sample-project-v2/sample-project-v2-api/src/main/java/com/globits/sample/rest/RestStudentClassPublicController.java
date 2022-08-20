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

import com.globits.sample.domain.StudentClass;
import com.globits.sample.service.StudentClassService;

@RestController
@RequestMapping("/public/studentclass")
public class RestStudentClassPublicController {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private StudentClassService studentClassService;

	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public Page<StudentClass> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<StudentClass> page = studentClassService.getList(pageIndex, pageSize);
		return page;
	}
	@RequestMapping(method = RequestMethod.GET)
	public Page<StudentClass> getListByParam(@RequestParam(name="page") int page, @RequestParam(name="size") int size) {
		Page<StudentClass> pageInfo = studentClassService.getList(page, size);
		return pageInfo;
	}

	
	@RequestMapping(value = "/{studentClassId}", method = RequestMethod.GET)
	public StudentClass getStudentClass(@PathVariable("studentClassId") String studentClassId) {
		StudentClass studentClass = studentClassService.findById(UUID.fromString(studentClassId));
		// building = new Building(building);
		return studentClass;
	}

	@RequestMapping(method = RequestMethod.POST)
	public StudentClass saveStudentClass(@RequestBody StudentClass studentClass) {
		return studentClassService.save(studentClass);
	}

	@RequestMapping(value = "/{studentClassId}", method = RequestMethod.PUT)
	public StudentClass updateStudentClass(@RequestBody StudentClass StudentClass, @PathVariable("studentClassId") Long StudentClassId) {
		return studentClassService.save(StudentClass);
	}

	@RequestMapping(value = "/{studentClassId}", method = RequestMethod.DELETE)
	public StudentClass removeStudentClass(@PathVariable("studentClassId") String studentClassId) {
		StudentClass StudentClass = studentClassService.delete(UUID.fromString(studentClassId));
		return StudentClass;
	}
}

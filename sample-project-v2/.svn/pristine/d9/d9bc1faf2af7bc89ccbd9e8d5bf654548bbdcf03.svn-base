package com.globits.sample.rest;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.globits.sample.domain.Student;
import com.globits.sample.dto.StudentDto;
import com.globits.sample.service.StudentService;
import com.globits.sample.utils.ImportExcel;

@RestController
@RequestMapping("/public/student")
public class RestStudentPublicController {
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public Page<StudentDto> getList(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<StudentDto> page = studentService.getByPage(pageIndex, pageSize);
		return page;
	}
	@RequestMapping(method = RequestMethod.GET)
	public Page<Student> getListByParam(@RequestParam(name="page") int page, @RequestParam(name="size") int size) {
		Page<Student> pageInfo = studentService.getList(page, size);
		return pageInfo;
	}

	@RequestMapping(method = RequestMethod.POST)
	public StudentDto saveStudent(@RequestBody StudentDto student) {
		return studentService.saveStudent(student);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteStudent(@RequestBody Student student) {
		if(student!=null && student.getId()!=null) {
			studentService.delete(student.getId());	
		}
	}
	@RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
	public Student updateStudent(@RequestBody Student Student, @PathVariable("studentId") String StudentId) {
		return studentService.save(Student);
	}

	@RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
	public void removeStudent(@PathVariable("studentId") String studentId) {
		studentService.delete(UUID.fromString(studentId));
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<StudentDto> listStudent() {
		return studentService.getAll();
	}
	
	@RequestMapping(value = "/importstudent", method = RequestMethod.POST)
	public ResponseEntity<?> importStudent(@RequestParam("uploadfile") MultipartFile uploadfile){
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(uploadfile.getBytes());
			List<StudentDto> list = ImportExcel.importStudent(bis);
			studentService.saveList(list);
			return new ResponseEntity<>("Import success!", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Import failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
}

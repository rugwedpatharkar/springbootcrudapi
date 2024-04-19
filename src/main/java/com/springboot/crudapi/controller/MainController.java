package com.springboot.crudapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crudapi.exception.ResourceNotFoundException;
import com.springboot.crudapi.model.Student;
import com.springboot.crudapi.repository.StudentRepository;

	@RestController
	@RequestMapping("/api/students")
	public class MainController {
	    @Autowired
	    private StudentRepository studentRepository;

	    @GetMapping
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    @PostMapping
	    public Student createStudent(@RequestBody Student student) {
	        return studentRepository.save(student);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
	        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
	        
	        student.setName(studentDetails.getName());
	        student.setAge(studentDetails.getAge());
	        student.setGrades(studentDetails.getGrades());
	        student.setSclass(studentDetails.getSclass());

	        Student updatedStudent = studentRepository.save(student);
	        return ResponseEntity.ok(updatedStudent);
	    }
 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
	        Student student = studentRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
	        
	        studentRepository.delete(student);
	        return ResponseEntity.ok().build();
	    }
	}


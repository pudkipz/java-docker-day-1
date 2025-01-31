package com.booleanuk.api.controller;

import com.booleanuk.api.model.Course;
import com.booleanuk.api.model.Student;
import com.booleanuk.api.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/students")
public class StudentController {
    private StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable int id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable int id, @RequestBody Student student) {
        Student studentToUpdate = studentRepository.findById(id).orElseThrow();
        studentToUpdate.setFirstName(studentToUpdate.getFirstName());
        studentToUpdate.setLastName(studentToUpdate.getLastName());
        studentToUpdate.setDateOfBirth(student.getDateOfBirth());
        studentToUpdate.setAverageGrade(student.getAverageGrade());
        return studentRepository.save(studentToUpdate);
    }

    @GetMapping("/{id}/courses")
    public Set<Course> getStudentsCourses(@PathVariable int id) {
        return studentRepository.findById(id).orElseThrow().getMyCourses();
    }
}

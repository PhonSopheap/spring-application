package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    @GetMapping("/api/v1/teacher")
    public List<Student> getAllTeacher(){
        return studentRepo.findAll();
    }
    @GetMapping("/api/v1/teacher/{id}")
    public Student getTeacherById(@PathVariable long id){
        return studentRepo.findById(id).orElse(new Student());
    }
    @PostMapping("/api/v1/teacher")
    public Student insertTeacher(@RequestBody Student student){
        return studentRepo.save(student);
    }
    @GetMapping("/api/v1/teacher/hello")
    public String sayHello(){
        return "Hello teacher";
    }
}

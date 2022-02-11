package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.service.StudentDto;
import ru.geekbrains.service.StudentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students";
    }

    @PostMapping
    public String updateStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult result) {
        if (result.hasErrors()) {
            return "student_form";
        }
        studentService.save(studentDto);
        return "redirect:/student";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new StudentDto());
        return "student_form";
    }

    @GetMapping("/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "student_form";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteById(id);
        return "redirect:/student";
    }
}

package com.example.crud_thymeleaf_jpa.controller;

import com.example.crud_thymeleaf_jpa.model.Classes;
import com.example.crud_thymeleaf_jpa.model.Student;
import com.example.crud_thymeleaf_jpa.service.IClassesService;
import com.example.crud_thymeleaf_jpa.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/one_page")
public class OnePageController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassesService classesService;

    //tự động đẩy list students vào tất cả các response của controller này
    @ModelAttribute("students")
    public List<Student> studentList() {
        return studentService.findAll();
    }

    @ModelAttribute("classes")
    public List<Classes> classesList() {
        return classesService.findAll();
    }

    @GetMapping
    public ModelAndView onePage() {
        ModelAndView modelAndView = new ModelAndView("one_page");
        modelAndView.addObject("test", "-1");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView onePageGet(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("one_page");
        if (id != 0) {
            Optional<Student> student = studentService.findById(id);
            if (student.isPresent()) {
                modelAndView.addObject("test", id);
                modelAndView.addObject("student", student.get());
                return modelAndView;
            }
        }
        modelAndView.addObject("test", "0");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/{id}")
    public String onePagePost(@ModelAttribute("student") Optional<Student> student,
                              RedirectAttributes redirectAttributes) {
        if (student.isPresent()) {
            if (student.get().getId() == 0) {
                redirectAttributes.addFlashAttribute("message", "Create successfully!");
            } else {
                redirectAttributes.addFlashAttribute("message", "Update successfully!");
            }
            studentService.save(student.get());
        } else {
            redirectAttributes.addFlashAttribute("message", "Fail");
        }
        return "redirect:http://localhost:8088/one_page";
    }

    @GetMapping("/delete/{id}")
    public String onePageDelete(@PathVariable("id") Long id,
                                RedirectAttributes redirectAttributes) {
        studentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Delete successfully!");
        return "redirect:http://localhost:8088/one_page";
    }
}

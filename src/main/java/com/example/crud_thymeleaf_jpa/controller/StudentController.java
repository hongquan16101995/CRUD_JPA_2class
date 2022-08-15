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
@RequestMapping("/student")
public class StudentController {
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
    public ModelAndView findAllStudent() {
        return new ModelAndView("display");
    }

    @GetMapping("/create")
    public ModelAndView createStudent() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("student") Optional<Student> student,
                         RedirectAttributes redirectAttributes) {
        if (student.isPresent()) {
            studentService.save(student.get());
        } else {
            redirectAttributes.addFlashAttribute("message", "Create fail!");
        }
        redirectAttributes.addFlashAttribute("message", "Create successfully!");
        return "redirect:/student";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateStudent(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("update");
        Optional<Student> student = studentService.findById(id);
        if (student.isPresent()) {
            modelAndView.addObject("student", student.get());
        } else {
            modelAndView.addObject("message", "Not exist student!");
        }
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("student") Optional<Student> student,
                         RedirectAttributes redirectAttributes) {
        if (student.isPresent()) {
            studentService.save(student.get());
        } else {
            redirectAttributes.addFlashAttribute("message", "Update fail!");
        }
        redirectAttributes.addFlashAttribute("message", "Update successfully!");
        return "redirect:/student";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id,
                         RedirectAttributes redirectAttributes) {
        studentService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Delete successfully!");
        return "redirect:/student";
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam("search") Optional<String> name) {
        ModelAndView modelAndView = new ModelAndView("display");
//        List<Student> students ;
        if (name.isPresent()) {
            List<Student> students = studentService.findBySearch(name.get());
            modelAndView.addObject("students", students);
        }
//        modelAndView.addObject("students", students);
        return modelAndView;
    }
}

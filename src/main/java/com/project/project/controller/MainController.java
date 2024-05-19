package com.project.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.project.entity.Employee;
import com.project.project.service.EmployeeService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {

    private EmployeeService EmployeeService;

    public MainController(EmployeeService EmployeeService) {
        this.EmployeeService = EmployeeService;
    }
    // ----------------------------------------------------------------------------------------------------------------

    @GetMapping("/")
    public String sample(org.springframework.ui.Model model) {
        model.addAttribute("employeeObj", new Employee());
        return "collection/register";
    }

    // ----------------------------------------------------------------------------------------------------------------
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("employeeObj") Employee employee, BindingResult result,
            HttpSession session) {

        if (result.hasErrors()) {
            return "collection/register";
        } else {
            Employee savedEmployee = EmployeeService.saveEmployee(employee);
            if (savedEmployee != null) {
                session.setAttribute("msg", "Employee saved successfully");
            } else {
                session.setAttribute("msg", "Something went wrong");
            }
            EmployeeService.saveEmployee(employee);
            return "redirect:/collection/dashboard";
        }
    }
    // ----------------------------------------------------------------------------------------------------------------

    @GetMapping("/collection/dashboard")
    public String dashboard(org.springframework.ui.Model model) {

        List<Employee> allEmployees = EmployeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);

        return "/collection/dashboard";
    }
    // ----------------------------------------------------------------------------------------------------------------

    @GetMapping("/delete/{empid}")
    public String deleteEmployee(@PathVariable int empid, HttpSession session) {
        Optional<Employee> dbEmployee = EmployeeService.getEmployeebyId(empid);
        if (dbEmployee.isEmpty()) {
            session.setAttribute("deleteMsg", "Employee with ID " + empid + " is not available");

        } else {
            EmployeeService.deleteEmployee(dbEmployee.get());
            session.setAttribute("deleteMsg", "Employee with ID " + empid + " has been deleted ");
        }
        return "redirect:/collection/dashboard";
    }

    // ----------------------------------------------------------------------------------------------------------------
    @GetMapping("/update/{empid}")
    public String UpdateEmployee(@PathVariable int empid, HttpSession session, org.springframework.ui.Model model) {
        Optional<Employee> dbEmployee = EmployeeService.getEmployeebyId(empid);
        if (dbEmployee.isEmpty()) {
            session.setAttribute("deleteMsg", "Employee with ID " + empid + " is not present in our records");
            return "redirect:/collection/dashboard";

        } else {
            model.addAttribute("employeeObj", dbEmployee.get());
            return "collection/register";

        }

    }
}

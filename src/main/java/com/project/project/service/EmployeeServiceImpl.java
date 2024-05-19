package com.project.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.project.entity.Employee;
import com.project.project.repository.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private repository repo;

    @Autowired
    public EmployeeServiceImpl(repository repo) {
        this.repo = repo;
    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public Employee saveEmployee(Employee employee) {
        return repo.save(employee);
    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public void deleteEmployee(Employee employee) {
        repo.delete(employee);
    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public Optional<Employee> getEmployeebyId(int empid) {
        Optional<Employee> employee = repo.findById(empid);
        return employee;
    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public void removeSessionsmg() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = attr.getRequest();
        HttpSession session = request.getSession();

        session.removeAttribute("msg");

    }

    // ----------------------------------------------------------------------------------------------------
    @Override
    public void removeSessionDeletedMsg() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletRequest request = attr.getRequest();
        HttpSession session = request.getSession();

        session.removeAttribute("deleteMsg");

    }
}
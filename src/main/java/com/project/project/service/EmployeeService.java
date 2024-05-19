package com.project.project.service;

import java.util.List;
import java.util.Optional;

import com.project.project.entity.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getEmployeebyId(int empid);

    void deleteEmployee(Employee employee);

    void removeSessionsmg();

    void removeSessionDeletedMsg();

}

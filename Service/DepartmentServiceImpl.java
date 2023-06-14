package Service;

import Entity.Employee;
import Exeption.DepartmentSearchException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;
import java.util.stream.Collectors;

public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;


    @Autowired
    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден"));


    }

    @Override
    public Employee getEmployeeWithMinSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment().getId() == departmentId)
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(() -> new DepartmentSearchException("Департамент не найден"));
    }

    @Override
    public Map<String, List<Employee>> getAll(Integer departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> departmentId == null ||
                        employee.getDepartment().getId() == departmentId)
                .collect(Collectors.groupingBy(
                        employee -> employee.getDepartment().getName(),
                        Collectors.mapping(e -> e, Collectors.toList())));
    }


}

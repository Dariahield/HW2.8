package Service;

import Entity.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee getEmployeeWithMaxSalary(int departmentId);

    Employee getEmployeeWithMinSalary(int departmentId);

    Map<String, List<Employee>> getAll(Integer departmentId);
}

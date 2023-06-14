package Service;

import Entity.Employee;
import Exeption.DepartmentSearchException;
import Exeption.EmployeeNotFoundException;
import Exeption.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static Entity.Department.DEPARTMENT_BY_ID;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int MAX_EMPLOYEES_COUNT = 10;
    private static List<Employee> employees = new ArrayList<>();

    static {
        Employee accounting1 = new Employee("Ирина", "Епифанова", 30500f, DEPARTMENT_BY_ID.get(1));
        Employee accounting2 = new Employee("Мария", "Козлова", 32600f, DEPARTMENT_BY_ID.get(1));

        Employee it1 = new Employee("Сергей", "Иванов", 60500f, DEPARTMENT_BY_ID.get(2));
        Employee it2 = new Employee("Кристина", "Сергеева", 61500f, DEPARTMENT_BY_ID.get(2));
        Employee it3 = new Employee("Дмитрий", "Гречишников", 70000f, DEPARTMENT_BY_ID.get(2));

        Employee support1 = new Employee("Алексей", "Петров", 40500f, DEPARTMENT_BY_ID.get(3));
        Employee support2 = new Employee("Петр", "Нечаев", 42500f, DEPARTMENT_BY_ID.get(3));
        Employee support3 = new Employee("Федор", "Николаев", 43500f, DEPARTMENT_BY_ID.get(3));
        Employee support4 = new Employee("Николай", "Щербаков", 44500f, DEPARTMENT_BY_ID.get(3));

        employees.add(accounting1);
        employees.add(accounting2);
        employees.add(it1);
        employees.add(it2);
        employees.add(it3);
        employees.add(support1);
        employees.add(support2);
        employees.add(support3);
        employees.add(support4);
    }


    @Override
    public Employee add(String firstName, String lastName, float salary, int departmentId) {

        if (employees.size() == MAX_EMPLOYEES_COUNT) {
            throw new EmployeeStorageIsFullException("Массив сотрудников переполнен");
        }

        Employee employee = new Employee(firstName, lastName, salary, DEPARTMENT_BY_ID.get(departmentId));

        if (employees.contains(employee)) {
            throw new DepartmentSearchException("В массиве уже есть такой сотрудник");
        }

        employees.add(employee);

        return employee;


    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = null;

        for (Employee e : employees) {
            if (e != null && firstName.equals(e.getFirstName()) && lastName.equals(e.getLastName())) {
                employee = e;
            }
        }

        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = find(firstName, lastName);

        for (Employee e : employees) {
            if (e.equals(employee)) {
                return e;
            }
        }

        return employee;
    }

    @Override
    public List<Employee> getAll() {
        return employees;
    }
}

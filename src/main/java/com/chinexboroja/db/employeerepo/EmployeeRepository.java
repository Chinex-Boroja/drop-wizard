package com.chinexboroja.db.employeerepo;

import com.chinexboroja.core.model.employee.Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeRepository {

    public static HashMap<Integer, Employee> map = new HashMap<>();

    static {
        map.put(1, new Employee(1, "Chinedu", "Ihedioha", "chinedu@gmail.com"));
        map.put(2, new Employee(2, "Uzo", "Graxx", "grx@gmail.com"));
        map.put(3, new Employee(3, "John", "Doe", "john@gmail.com"));
    }

    public static List<Employee> getEmployees() {
        return new ArrayList<>(map.values());
    }

    public static Employee getEmployee(Integer id) {
        return map.get(id);
    }

    public static void updateEmployee(Integer id, Employee employee) {
        map.put(id, employee);
    }

    public static void createEmployee(Integer id, Employee employee) {
        map.put(id, employee);
    }

    public static void removeEmployee(Integer id) {
        map.remove(id);
    }
}

package features.jep_476_module_import;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// 1. Module Import Declarations
// 2. Ambiguous Imports?

public class ModuleApp {
  public static void main(String[] args) {
    List<Employee> employees = List.of(
        new Employee("Alice", "HR", 70000),
        new Employee("Bob", "IT", 90000),
        new Employee("Charlie", "HR", 60000),
        new Employee("David", "IT", 80000)
    );

    // Group employees by department
    Map<String, List<Employee>> employeesByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::department));

    System.out.println("Employees grouped by department: " + employeesByDept);

    // Find the highest-paid employee
    Employee highestPaidEmployee = employees.stream()
        .max(Comparator.comparingInt(Employee::salary))
        .orElse(null);

    System.out.println("Highest-paid employee: " + highestPaidEmployee);

    // Create a report of employees' names and salaries
    Function<Employee, String> reportMapper = e -> e.name() + ": $" + e.salary();
    List<String> employeeReport = employees.stream()
        .map(reportMapper)
        .collect(Collectors.toList());

    System.out.println("Employee Report: " + employeeReport);
  }
}

record Employee(String name, String department, int salary) {}

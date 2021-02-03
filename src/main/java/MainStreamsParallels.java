import java.util.ArrayList;
import java.util.List;

public class MainStreamsParallels {

    public static void main(String[] args) {
        long timeBefore;
        long timeAfter;
        List<Employee> employees = new ArrayList<>();

        for(int i = 0; i < 100; i++){
            employees.add(new Employee("employee" + i, 1000));
            employees.add(new Employee("employee" + i, 1500));
            employees.add(new Employee("employee" + i, 100));
            employees.add(new Employee("employee" + i, 800));
            employees.add(new Employee("employee" + i, 300));
            employees.add(new Employee("employee" + i, 500));
            employees.add(new Employee("employee" + i, 201));
            employees.add(new Employee("employee" + i, 150));
        }
        timeBefore = System.currentTimeMillis();
        System.out.println("Secuential stream count: " +
                employees.stream().filter(employee -> employee.getSalary() > 200).count());
        timeAfter = System.currentTimeMillis();
        System.out.println("secuential time taken: " + (timeAfter-timeBefore));


        timeBefore = System.currentTimeMillis();
        System.out.println("Parallel stream count: " +
                //employees.parallelStream().filter(employee -> employee.getSalary() > 200).count());
                employees.parallelStream().filter(employee -> employee.getSalary() > 200).parallel().count());

        timeAfter = System.currentTimeMillis();
        System.out.println("Parallel time taken: " + (timeAfter-timeBefore));

    }
}

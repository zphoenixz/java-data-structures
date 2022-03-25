package Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class S4Lists {
    public static void main(String[] args) {
//        arrayListExample();
        vectorsExample();
    }

    private static void arrayListExample(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Jane", "Jones", 123));
        employeeList.add(new Employee("John", "Doe", 4567));
        employeeList.set(1, new Employee("John", "Adams", 4568));
        employeeList.add(2, new Employee("John", "Doe", 4567));
        employeeList.remove(2);
//        employeeList.forEach(employee -> System.out.println(employee));
    }

    private static void vectorsExample(){
        List<Employee> employeeList = new Vector<>();
        employeeList.add(new Employee("Jane", "Jones", 123));
        employeeList.add(new Employee("John", "Doe", 4567));
        employeeList.add(new Employee("Mary", "Smith", 22));
        employeeList.add(new Employee("Mike", "Wilson", 3245));

        List<Employee> employeeList2 = employeeList;
        employeeList2.get(0).setFirstName("John");

        System.out.println(employeeList.get(0));
        System.out.println(employeeList2.get(0));
    }
}

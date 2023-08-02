package common;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author zt
 * @since 2023/6/27 19:46
 **/
public class EmployeeService {

    private ArrayList<Employee> all;

    public EmployeeService() {

        all = new ArrayList<>();
        all.add(new Employee(1, "老大", 44, 20000));
        all.add(new Employee(2, "老二", 35, 18764));
        all.add(new Employee(3, "老三", 32, 15674));
        all.add(new Employee(4, "老四", 28, 12985));
        all.add(new Employee(5, "老五", 24, 9875));
        all.add(new Employee(6, "老六", 21, 7456));
    }

    public ArrayList<Employee> get(Predicate<Employee> predicate) {

        ArrayList<Employee> result = new ArrayList<>();
        for (Employee emp : all) {
            if (predicate.test(emp)) {
                result.add(emp);
            }
        }
        return result;
    }
}

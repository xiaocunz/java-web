package net.xiaocun;

import com.thoughtworks.xstream.XStream;
import net.xiaocun.bean.Department;
import net.xiaocun.bean.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxiaocun on 2017/4/27.
 */
public class AppWithAnnotaion {
    public static void main(String[] args) {
        Department dept = createDepartment();

        XStream xstream = new XStream();

        // Using annotations in class Department
        xstream.processAnnotations(Department.class);

        // Using annotations in class Employee
        xstream.processAnnotations(Employee.class);

        String xml = xstream.toXML(dept);

        System.out.println(xml);
    }

    public static Department createDepartment() {
        Department dept = new Department();
        dept.setDeptNo(10);
        dept.setDeptName("ACCOUNTING");

        Employee king = new Employee(7839, "KING", 5000f);
        Employee clark = new Employee(7839, "CLARK", 2450f);
        Employee miller = new Employee(7839, "MILLER", 1300f);

        List<Employee> list = new ArrayList<Employee>();
        list.add(king);
        list.add(clark);
        list.add(miller);
        dept.setEmployees(list);
        return dept;
    }
}

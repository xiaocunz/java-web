package net.xiaocun.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by zxiaocun on 2017/4/27.
 */
@XStreamAlias("dept")
public class Department {

    @XStreamAlias("no")
    @XStreamAsAttribute
    private int deptNo;

    @XStreamAlias("name")
    private String deptName;

    @XStreamImplicit
    private List<Employee> employees;

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}

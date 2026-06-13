public class Employee {
    @Property("emp_id")
    @ReadOnly
    private int id;

    @Property("full_name")
    private String name;

    @Property("dept")
    @DefaultValue("GENERAL")
    private String department;

    @Property("salary")
    private double salary;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name=" + name
                + ", department=" + department + ", salary=" + salary + "}";
    }
}

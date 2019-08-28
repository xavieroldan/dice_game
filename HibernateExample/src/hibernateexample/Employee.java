package hibernateexample;
// Generated 27-ago-2019 13:33:51 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Employee generated by hbm2java
 */
public class Employee implements java.io.Serializable
{

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer salary;
    private Set<Phone> phones = new HashSet<Phone>(0);

    public Employee()
    {
    }

    public Employee(String firstName, String lastName, Integer salary)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, Integer salary, Set<Phone> phones)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.phones = phones;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getSalary()
    {
        return this.salary;
    }

    public void setSalary(Integer salary)
    {
        this.salary = salary;
    }

    public Set<Phone> getPhones()
    {
        return this.phones;
    }

    public void setPhones(Set<Phone> phones)
    {
        this.phones = phones;
    }

}

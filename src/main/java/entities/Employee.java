package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

@Entity
public class Employee {
    @Id
    private int employee_number;
    @Column
    private String lastName;
    @Column
    private String firstName;
    @Column
    private String extension;
    @Column
    private String email;
    @Column
    private int officeCode;
    @Column
    private int reportsTo;
    @Column
    private String jobTitle;

    public Employee(String lastName, String firstName, String extension, String email, int officeCode, int reportsTo, String jobTitle) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.officeCode = officeCode;
        this.reportsTo = reportsTo;
        this.jobTitle = jobTitle;
    }

    public int getEmployee_number() {
        return employee_number;
    }

    public void setEmployee_number(int employee_number) {
        this.employee_number = employee_number;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(int officeCode) {
        this.officeCode = officeCode;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getJob_title() {
        return jobTitle;
    }

    public void setJob_title(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}

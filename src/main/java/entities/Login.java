package entities;

import orm.annotations.Column;
import orm.annotations.Entity;

@Entity
public class Login {
    @Column
    private int customerNumber;
    @Column
    private int employeeNumber;
    @Column
    private String login;
    @Column
    private String password;

    public Login(int customerNumber, int employeeNumber, String login, String password) {
        this.customerNumber = customerNumber;
        this.employeeNumber = employeeNumber;
        this.login = login;
        this.password = password;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

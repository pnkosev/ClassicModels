package entities;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

@Entity
public class Customer {
    @Id
    private int customerNumber;
    @Column
    private String customerName;
    @Column
    private String contactLastName;
    @Column
    private String contactFirstName;
    @Column
    private String phone;
    @Column
    private String addressLine1;
    @Column
    private String addressLine2;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String postalCode;
    @Column
    private String country;
    @Column
    private int salesRepEmployeeNumber;
    @Column
    private double creditLimit;

    public Customer() {}

    public Customer(String customerName,
                    String contactLastName,
                    String contactFirstName,
                    String phone,
                    String addressLine1,
                    String addressLine2,
                    String city,
                    String state,
                    String postalCode,
                    String country,
                    int salesRepEmployeeNumber,
                    double creditLimit) {
        this.customerName = customerName;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
        this.creditLimit = creditLimit;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return contactLastName;
    }

    public void setCustomerLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getCustomerFirstName() {
        return contactFirstName;
    }

    public void setCustomerFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSalesRepEmployeeNumber() {
        return salesRepEmployeeNumber;
    }

    public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public String toString() {
        return String.format("%s with id %s is from %s", this.customerName, this.customerNumber, this.city);
    }
}

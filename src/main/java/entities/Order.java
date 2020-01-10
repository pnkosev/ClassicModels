package entities;

import enums.Status;
import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.util.Date;

@Entity
public class Order {
    @Id
    private int orderNumber;
    @Column
    private Date orderDate;
    @Column
    private Date requiredDate;
    @Column
    private Date shippedDate;
    @Column
    private Status status;
    @Column
    private String state;
    @Column
    private String comments;
    @Column
    private int customerNumber;

    public Order(Date orderDate, Date requiredDate, Date shippedDate, Status status, String state, String comments, int customerNumber) {
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.state = state;
        this.comments = comments;
        this.customerNumber = customerNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }
}

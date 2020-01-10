package entities;

import orm.annotations.Column;
import orm.annotations.Entity;

@Entity
public class LiensImage {
    @Column
    private int customerNumber;
    @Column
    private int employeeNumber;
    @Column
    private String productCode;
    @Column
    private int imageId;

    public LiensImage(int customerNumber, int employeeNumber, String productCode, int imageId) {
        this.customerNumber = customerNumber;
        this.employeeNumber = employeeNumber;
        this.productCode = productCode;
        this.imageId = imageId;
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

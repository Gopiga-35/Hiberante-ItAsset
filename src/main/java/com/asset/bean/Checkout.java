package com.asset.bean;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "checkouts")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int checkoutID;

    @Column(name = "asset_tag", nullable = false)
    private String assetTag;

    @Column(name = "employee_id", nullable = false)
    private String employeeID;

    @Temporal(TemporalType.DATE)
    @Column(name = "checkout_date", nullable = false)
    private Date checkoutDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date", nullable = false)
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "status", nullable = false)
    private String status;

    // Getters and Setters

    public int getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(int checkoutID) {
        this.checkoutID = checkoutID;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

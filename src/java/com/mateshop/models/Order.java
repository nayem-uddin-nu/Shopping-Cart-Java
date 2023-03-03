/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mateshop.models;

/**
 *
 * @author nayem
 */
public class Order extends Product{
    private int orderId;
    private int uid;
    private int quantity; 
    private String date;


    public Order() {
    }

    public Order(int orderId, int uid, int quantity) {
        this.orderId = orderId;
        this.uid = uid;
        this.quantity = quantity;
    }

    public Order(int uid, int quantity) {
        this.uid = uid;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
}

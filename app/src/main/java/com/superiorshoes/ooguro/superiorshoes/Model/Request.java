package com.superiorshoes.ooguro.superiorshoes.Model;

import java.util.List;

public class Request {
    private String phone;
    private String name;
    private String address;
    private String total;
    private List<Order> cloths; // List of clothing

    public Request() {
    }

    public Request(String phone, String name, String address, String total, List<Order> cloths) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.cloths = cloths;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getCloths() {
        return cloths;
    }

    public void setCloths(List<Order> cloths) {
        this.cloths = cloths;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

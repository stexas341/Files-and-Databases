/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author lympe
 */
public class Rent {

    int rent_id, customer_id, vehicle_id;
    String info, firstname, lastname;
    int timeperiod;
    double price;
    public int getrent_id() {
        return rent_id;
    }

    public int getcustomer_id() {
        return customer_id;
    }

    public void setcustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getvehicle_id() {
        return vehicle_id;
    }

    public void setvehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getTimeperiod() {
        return timeperiod;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTimeperiod(int timeperiod) {
        this.timeperiod = timeperiod;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}

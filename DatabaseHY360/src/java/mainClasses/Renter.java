/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author lympe
 */
public class Renter {

    int renter_id;
    String firstname, lastname, address, dateofbirth, licencenumber;
    double cardnumber;

    public int getrenter_id() {
        return renter_id;
    }

    public void setrenter_id(int renter_id) {
        this.renter_id = renter_id;
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

    public String getAddress() {
        return address;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public double getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(double cardnumber) {
        this.cardnumber = cardnumber;
    }


    public void setLicencenumber(String licencenumber) {
        this.licencenumber = licencenumber;
    }

    public String getLicencenumber() {
        return licencenumber;
    }


}

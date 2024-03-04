/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

/**
 *
 * @author lympe
 */
public class Service {

    int service_id, vehicle_id;
    String vehicle;
    String enddate;

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public void setVehcile(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setEndDate(String enddate) {
        this.enddate = enddate;
    }

    public int getService_id() {
        return service_id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getEndDate() {
        return enddate;
    }
}

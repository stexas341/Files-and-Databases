/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import com.google.gson.Gson;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import mainClasses.Service;

/**
 *
 * @author lympe
 */
public class EditServiceTable {

    public void createServiceTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE service"
                + "(service_id INTEGER not NULL AUTO_INCREMENT,"
                + "    vehicle_id INTEGER not null ,"
                + "    vehicle VARCHAR(50) not null ,"
                + "    enddate DATE not null,"
                + " PRIMARY KEY (service_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void addServiceFromJSON(String json) throws ClassNotFoundException {
        Service user = jsonToService(json);
        addNewService(user);
    }

    public Service jsonToService(String json) {
        Gson gson = new Gson();

        Service user = gson.fromJson(json, Service.class);
        return user;
    }

    public void addNewService(Service user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String licencePlate = RandomLicencePlate.generateLicencePlate();
            String insertQuery = "INSERT INTO"
                    + " service (service_id,vehicle_id,vehicle,enddate)"
                    + " VALUES ("
                    + "'" + user.getService_id() + "',"
                    + "'" + user.getVehicle_id() + "',"
                    + "'" + user.getVehicle() + "',"
                    + "'" + user.getEndDate() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The service was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("THERE IS A PROBLEM HERE " + ex.getMessage());
        }
    }


}

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
import mainClasses.Rent;

/**
 *
 * @author lympe
 */
public class EditRentsTable {

    public void createRentTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE rents"
                + "(rent_id INTEGER not NULL AUTO_INCREMENT, "
                + "    customer_id INTEGER , "
                + "    vehicle_id INTEGER , "
                + "    firstname VARCHAR(30) ,"
                + "    lastname VARCHAR(50)  ,"
                + "    timeperiod INTEGER ,"
                + "    price DOUBLE ,"
                + "    info VARCHAR(30),"
                + " PRIMARY KEY (rent_id))";
        stmt.execute(query);
        stmt.close();

    }

    public void addRentFromJSON(String json) throws ClassNotFoundException {
        Rent user = jsonToRent(json);
        addNewRent(user);
    }

    public Rent jsonToRent(String json) {
        Gson gson = new Gson();

        Rent user = gson.fromJson(json, Rent.class);
        return user;
    }

    public void addNewRent(Rent user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();
            String insertQuery = "INSERT INTO "
                    + " rents (customer_id,vehicle_id,firstname,lastname,timeperiod,price,info)"
                    + " VALUES ("
                    + "'" + user.getcustomer_id() + "',"
                    + "'" + user.getvehicle_id() + "',"
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getTimeperiod() + "',"
                    + "'" + user.getPrice() + "',"
                    + "'" + user.getInfo() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The rent was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("THERE IS A PROBLEM HERE " + ex.getMessage());
        }
    }

}

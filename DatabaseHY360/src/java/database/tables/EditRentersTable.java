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
import mainClasses.Renter;

/**
 *
 * @author lympe
 */
public class EditRentersTable {

    public void createRenterTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE renters "
                + "(renter_id INTEGER not NULL AUTO_INCREMENT, "
                + "    firstname VARCHAR(30) not null ,"
                + "    lastname VARCHAR(50) not null ,"
                + "    address VARCHAR(32) not null,"
                + "    dateofbirth DATE not null,"
                + "    licencenumber VARCHAR(30) not null,"
                + "    cardnumber DOUBLE not null,"
                + " PRIMARY KEY (renter_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void addRenterFromJSON(String json) throws ClassNotFoundException {
        Renter user = jsonToRenter(json);
        addNewRenter(user);
    }

    public Renter jsonToRenter(String json) {
        Gson gson = new Gson();

        Renter user = gson.fromJson(json, Renter.class);
        return user;
    }

    public void addNewRenter(Renter user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();
            Statement stmt = con.createStatement();

            // Use PreparedStatement to avoid SQL injection
            String insertQuery = "INSERT INTO "
                    + "renters (firstname, lastname, address, dateofbirth, licencenumber, cardnumber) "
                    + "VALUES ("
                    + "'" + user.getFirstname() + "',"
                    + "'" + user.getLastname() + "',"
                    + "'" + user.getAddress() + "',"
                    + "'" + user.getDateofbirth() + "',"
                    + "'" + user.getLicencenumber() + "',"
                    + "'" + user.getCardnumber() + "'"
                    + ")";


            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The renter was successfully added in the database.");
            stmt.close();
        } catch (SQLException ex) {
            // Log the exception and consider throwing a custom exception or handling the error appropriately
            System.out.println("Error adding renter to the database: " + ex.getMessage());
        }
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import mainClasses.Scooter;
import database.tables.RandomLicencePlate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lympe
 */
public class EditScootersTable {

    public void createScootersTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE scooters "
                + "(scooter_id INTEGER not NULL AUTO_INCREMENT, "
                + "    brand VARCHAR(30) not null unique,"
                + "    model VARCHAR(50) not null unique,"
                + "    color VARCHAR(32) not null,"
                + "    autonomy INTEGER not null,"
                + "    available INTEGER not null,"
                + "      cost DOUBLE not null,"
                + "      insurance DOUBLE,"
                + " PRIMARY KEY (scooter_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void addScooterFromJSON(String json) throws ClassNotFoundException {
        Scooter user = jsonToScooter(json);
        addNewScooter(user);
    }

    public Scooter jsonToScooter(String json) {
        Gson gson = new Gson();

        Scooter user = gson.fromJson(json, Scooter.class);
        return user;
    }

    public void addNewScooter(Scooter user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String licencePlate = RandomLicencePlate.generateLicencePlate();
            String insertQuery = "INSERT INTO "
                    + " scooters (brand,model,color,autonomy,available,cost,insurance)"
                    + " VALUES ("
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getAutonomy() + "',"
                    + "'" + user.getAvailable() + "',"
                    + "'" + user.getCost() + "',"
                    + "'" + user.getInsurance() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The scooter was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("THERE IS A PROBLEM HERE " + ex.getMessage());
        }
    }

    public ArrayList<Scooter> databaseToScooter(int available) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Scooter> Scooters = new ArrayList<>();
        try {
            String query = "SELECT * FROM scooters WHERE available = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, available);
                try ( ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        String json = DB_Connection.getResultsToJSON(rs);
                        Gson gson = new Gson();
                        Scooter scooter = gson.fromJson(json, Scooter.class);
                        Scooters.add(scooter);
                    }
                }
            }
            return Scooters;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updatescooterav(int lic) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE scooters SET available='0' WHERE scooter_id = '" + lic + "'";
        stmt.executeUpdate(update);
    }

}

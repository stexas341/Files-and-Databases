/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mainClasses.Motorcycle;

/**
 *
 * @author lympe
 */

public class EditMotorcyclesTable {

    public void createMotorcyclesTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String query = "CREATE TABLE motorcycles "
                + "(licenceplate INTEGER not NULL AUTO_INCREMENT,"
                + "    brand VARCHAR(30) not null unique,"
                + "    model VARCHAR(50) not null unique,"
                + "    color VARCHAR(32) not null,"
                + "    autonomy INTEGER not null,"
                + "    type VARCHAR(30) not null,"
                + "    available INTEGER not null,"
                + "    cost DOUBLE not null,"
                + "    insurance DOUBLE,"
                + " PRIMARY KEY (licenceplate))";
        stmt.execute(query);
        stmt.close();
    }

    public void addMotorcycleFromJSON(String json) throws ClassNotFoundException {
        Motorcycle user = jsonToMotorcycle(json);
        addNewMotorcycle(user);
    }

    public Motorcycle jsonToMotorcycle(String json) {
        Gson gson = new Gson();

        Motorcycle user = gson.fromJson(json, Motorcycle.class);
        return user;
    }

    public void addNewMotorcycle(Motorcycle user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            String licencePlate = RandomLicencePlate.generateLicencePlate();

            Statement stmt = con.createStatement();
            String insertQuery = "INSERT INTO "
                    + " motorcycles (brand,model,color,autonomy,type,available,cost,insurance)"
                    + " VALUES ("
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getAutonomy() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getAvailable() + "',"
                    + "'" + user.getCost() + "',"
                    + "'" + user.getInsurance() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The motorcycle was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("THERE IS A PROBLEM HERE " + ex.getMessage());
        }
    }

    public ArrayList<Motorcycle> databaseToMotorcycle(int available) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Motorcycle> motors = new ArrayList<>();
        try {
            String query = ("SELECT * FROM motorcycles WHERE available = ?");
            try ( PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, available);
                try ( ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        String json = DB_Connection.getResultsToJSON(rs);
                        Gson gson = new Gson();
                        Motorcycle motor = gson.fromJson(json, Motorcycle.class);
                        motors.add(motor);
                    }
                }

            }
            return motors;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updatemotorav(int lic) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE motorcycles SET available='0' WHERE licenceplate = '" + lic + "'";
        stmt.executeUpdate(update);
    }


}

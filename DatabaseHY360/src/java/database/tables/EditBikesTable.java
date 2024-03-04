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
import mainClasses.Bike;

/**
 *
 * @author lympe
 */
public class EditBikesTable {


    public void createBikesTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String setIncrement = "SET @@auto_increment_increment=14;";
        stmt.execute(setIncrement);

        String query = "CREATE TABLE bikes "
                + "(bike_id INTEGER not NULL AUTO_INCREMENT, "
                + "    brand VARCHAR(30) not null unique,"
                + "    model VARCHAR(50) not null unique,"
                + "    color VARCHAR(32) not null,"
                + "    available INTEGER not null,"
                + "    autonomy INTEGER not null,"
                + "    cost DOUBLE not null,"
                + "    insurance DOUBLE,"
                + " PRIMARY KEY (bike_id))";
        stmt.execute(query);
        stmt.close();
    }

    public void addBikeFromJSON(String json) throws ClassNotFoundException {
        Bike user = jsonToBike(json);
        addNewBike(user);
    }

    public Bike jsonToBike(String json) {
        Gson gson = new Gson();

        Bike user = gson.fromJson(json, Bike.class);
        return user;
    }

    public void addNewBike(Bike user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();


            Statement stmt = con.createStatement();

            String licencePlate = RandomLicencePlate.generateLicencePlate();

            String insertQuery = "INSERT INTO "
                    + " bikes (brand,model,color,available,autonomy,cost,insurance)"
                    + " VALUES ("
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getAvailable() + "',"
                    + "'" + user.getAutonomy() + "',"
                    + "'" + user.getCost() + "',"
                    + "'" + user.getInsurance() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The bike was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("THERE IS A PROBLEM HERE " + ex.getMessage());
        }
    }

    public ArrayList<Bike> databaseToBike(int available) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Bike> bikes = new ArrayList<>();

        try {
            String query = "SELECT * FROM bikes WHERE available = ?";
            try ( PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, available);
                try ( ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        String json = DB_Connection.getResultsToJSON(rs);
                        Gson gson = new Gson();
                        Bike bike = gson.fromJson(json, Bike.class);
                        bikes.add(bike);
                    }
                }
            }
            return bikes;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updatebikeav(int lic) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE bikes SET available='0' WHERE bike_id = '" + lic + "'";
        stmt.executeUpdate(update);
    }

}

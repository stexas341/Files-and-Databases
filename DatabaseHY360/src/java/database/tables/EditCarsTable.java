package database.tables;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import database.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import mainClasses.Car;
import java.sql.ResultSet;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lympe
 */
public class EditCarsTable {

    public void createCarsTable() throws SQLException, ClassNotFoundException {

        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();

        String query = "CREATE TABLE cars "
                + "(licenceplate INTEGER not NULL AUTO_INCREMENT, "
                + "    brand VARCHAR(30) not null ,"
                + "    model VARCHAR(50) not null ,"
                + "    color VARCHAR(32) not null,"
                + "    autonomy INTEGER not null,"
                + "    type VARCHAR(30) not null,"
                + "    passengers INTEGER not null,"
                + "    available INTEGER not null,"
                + "    cost DOUBLE not null,"
                + "    insurance DOUBLE,"
                + " PRIMARY KEY (licenceplate))";
        stmt.execute(query);
        stmt.close();
    }

    public void addCarFromJSON(String json) throws ClassNotFoundException {
        Car user = jsonToCar(json);
        addNewCar(user);
    }

    public Car jsonToCar(String json) {
        Gson gson = new Gson();

        Car user = gson.fromJson(json, Car.class);
        return user;
    }

    public void addNewCar(Car user) throws ClassNotFoundException {
        try {
            Connection con = DB_Connection.getConnection();

            Statement stmt = con.createStatement();

            String licencePlate = RandomLicencePlate.generateLicencePlate();
            String insertQuery = "INSERT INTO "
                    + " cars (brand,model,color,autonomy,type,passengers,available,cost,insurance)"
                    + " VALUES ("
                    + "'" + user.getBrand() + "',"
                    + "'" + user.getModel() + "',"
                    + "'" + user.getColor() + "',"
                    + "'" + user.getAutonomy() + "',"
                    + "'" + user.getType() + "',"
                    + "'" + user.getPassengers() + "',"
                    + "'" + user.getAvailable() + "',"
                    + "'" + user.getCost() + "',"
                    + "'" + user.getInsurance() + "'"
                    + ")";
            //stmt.execute(table);
            System.out.println(insertQuery);
            stmt.executeUpdate(insertQuery);
            System.out.println("# The car was successfully added in the database.");

            /* Get the member id from the database and set it to the member */
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("THERE IS A PROBLEM HERE " + ex.getMessage());
        }
    }

    public ArrayList<Car> databaseToCar(int available) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        ArrayList<Car> cars = new ArrayList<>();

        try {
            String query = "SELECT * FROM cars WHERE available = ?";

            try ( PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setInt(1, available);

                try ( ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        String json = DB_Connection.getResultsToJSON(rs);
                        Gson gson = new Gson();
                        Car car = gson.fromJson(json, Car.class);
                        cars.add(car);
                    }
                }
            }
            return cars;
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updatecarav(int lic) throws SQLException, ClassNotFoundException {
        Connection con = DB_Connection.getConnection();
        Statement stmt = con.createStatement();
        String update = "UPDATE cars SET available='0' WHERE licenceplate = '" + lic + "'";
        stmt.executeUpdate(update);
    }

}

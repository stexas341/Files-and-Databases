/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mainClasses;

import database.DB_Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stsfa
 */
public class Questions {

    static public String submitSQLQuery(String query) throws SQLException {

        try {
            Connection con = DB_Connection.getConnection();
            Statement statement = con.createStatement();
            String result = "";

            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {

                for (int i = 1; i <= columnsNumber; i++) {
                    String columnValue = rs.getString(i);
                    result += rsmd.getColumnName(i) + ": " + columnValue + " ";
                }
                result += "\n";
            }

            return result;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Questions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}

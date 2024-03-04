/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditBikesTable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Bike;

/**
 *
 * @author stsfa
 */
public class availableBikes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Create an instance of EditCarsTable
            EditBikesTable ebt = new EditBikesTable();
            ArrayList<Bike> bikes = ebt.databaseToBike(1);
            try ( PrintWriter out = response.getWriter()) {
                for (Bike bike : bikes) {
                    out.println("<!DOCTYPE html>");
                    out.println("<div>");
                    out.println("Brand: " + bike.getBrand());
                    out.println("Model: " + bike.getModel());
                    out.println("Color: " + bike.getColor());
                    out.println("Autonomy: " + bike.getAutonomy());
                    out.println("Available: " + bike.getAvailable());
                    out.println("Cost: " + bike.getCost());
                    out.println("Insurance: " + bike.getInsurance());
                    out.println("<button class=\"button\" onclick='rent1()'>Rent</button>\n");
                    out.println("</div><br>");
                }
                response.setStatus(200);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(availableCars.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}

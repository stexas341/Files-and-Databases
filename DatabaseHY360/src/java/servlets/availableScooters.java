/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditScootersTable;
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
import mainClasses.Scooter;

/**
 *
 * @author stsfa
 */
public class availableScooters extends HttpServlet {

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
            EditScootersTable ect = new EditScootersTable();
            ArrayList<Scooter> scooters = ect.databaseToScooter(1);
            try ( PrintWriter out = response.getWriter()) {
                for (Scooter scooter : scooters) {
                    out.println("<!DOCTYPE html>");
                    out.println("<div>");
                    out.println("Brand: " + scooter.getBrand());
                    out.println("Model: " + scooter.getModel());
                    out.println("Color: " + scooter.getColor());
                    out.println("Autonomy: " + scooter.getAutonomy());
                    out.println("Available: " + scooter.getAvailable());
                    out.println("Cost: " + scooter.getCost());
                    out.println("Insurance: " + scooter.getInsurance());
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

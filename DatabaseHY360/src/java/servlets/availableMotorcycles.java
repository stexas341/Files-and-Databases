/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditMotorcyclesTable;
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
import mainClasses.Motorcycle;

/**
 *
 * @author stsfa
 */
public class availableMotorcycles extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
            EditMotorcyclesTable emt = new EditMotorcyclesTable();
            ArrayList<Motorcycle> motors = emt.databaseToMotorcycle(1);
            try ( PrintWriter out = response.getWriter()) {
                for (Motorcycle motor : motors) {
                    out.println("<!DOCTYPE html>");
                    out.println("<div>");
                    out.println("Brand: " + motor.getBrand());
                    out.println("Model: " + motor.getModel());
                    out.println("Color: " + motor.getColor());
                    out.println("Type: " + motor.getType());
                    out.println("Autonomy: " + motor.getAutonomy());
                    out.println("Available: " + motor.getAvailable());
                    out.println("Licence Plate: " + motor.getLicencePlate());
                    out.println("Cost: " + motor.getCost());
                    out.println("Insurance: " + motor.getInsurance());
                    out.println("<button class=\"button\" onclick='rent()'>Rent</button>\n");
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

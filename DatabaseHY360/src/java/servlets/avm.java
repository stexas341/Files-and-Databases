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
public class avm extends HttpServlet {

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
            EditMotorcyclesTable ect = new EditMotorcyclesTable();
            ArrayList<Motorcycle> motorsav = ect.databaseToMotorcycle(1);
            ArrayList<Motorcycle> motorsre = ect.databaseToMotorcycle(0);

            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");

                out.println("<div style='display: flex;'>");
                // Display available cars
                out.println("<div style='margin-right: 20px;'>");
                out.println("<span style='text-decoration: underline; font-weight: bold;'>AVAILABLE</span>");
                for (Motorcycle motora : motorsav) {
                    out.println("<div>");
                    out.println("Brand: " + motora.getBrand());
                    out.println("Model: " + motora.getModel());
                    out.println("Color: " + motora.getColor());
                    out.println("Licence Plate: " + motora.getLicencePlate());
                    out.println("</div><br>");
                }
                out.println("</div>");

                // Display rented cars
                out.println("<span style='text-decoration: underline; font-weight: bold;'>RENTED</span>");
                out.println("\n");
                out.println("<div>");
                for (Motorcycle motorr : motorsre) {
                    out.println("<div>");
                    out.println("Brand: " + motorr.getBrand());
                    out.println("Model: " + motorr.getModel());
                    out.println("Color: " + motorr.getColor());
                    out.println("Licence Plate: " + motorr.getLicencePlate());
                }
                out.println("</div>");
                out.println("</div>");

                out.println("</body>");
                out.println("</html>");

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

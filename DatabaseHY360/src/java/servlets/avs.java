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
public class avs extends HttpServlet {

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
            EditScootersTable ect = new EditScootersTable();
            ArrayList<Scooter> Scooterav = ect.databaseToScooter(1);
            ArrayList<Scooter> Scooterre = ect.databaseToScooter(0);

            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");

                out.println("<div style='display: flex;'>");
                // Display available cars
                out.println("<div style='margin-right: 20px;'>");
                out.println("<span style='text-decoration: underline; font-weight: bold;'>AVAILABLE</span>");
                out.println("<br>");
                for (Scooter Scooter : Scooterav) {
                    out.println("<div>");
                    out.println("Brand: " + Scooter.getBrand());
                    out.println("Model: " + Scooter.getModel());
                    out.println("Color: " + Scooter.getColor());
                    out.println("Licence Plate: " + Scooter.getscooter_id());
                    out.println("</div><br>");
                }
                out.println("</div>");

                // Display rented cars
                out.println("<div>");
                out.println("<span style='text-decoration: underline; font-weight: bold;'>RENTED</span>");
                out.println("<br>");
                for (Scooter Scooterr : Scooterre) {
                    out.println("<div>");
                    out.println("Brand: " + Scooterr.getBrand());
                    out.println("Model: " + Scooterr.getModel());
                    out.println("Color: " + Scooterr.getColor());
                    out.println("Licence Plate: " + Scooterr.getscooter_id());
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

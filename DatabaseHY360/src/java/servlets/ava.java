/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditCarsTable;
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
import mainClasses.Car;

/**
 *
 * @author stsfa
 */
public class ava extends HttpServlet {

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
            EditCarsTable ect = new EditCarsTable();
            ArrayList<Car> carsav = ect.databaseToCar(1);
            ArrayList<Car> carsre = ect.databaseToCar(0);

            try ( PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<body>");

                out.println("<div style='display: flex;'>");
                // Display available cars
                out.println("<div style='margin-right: 20px;'>");
                out.println("<span style='text-decoration: underline; font-weight: bold;'>AVAILABLE</span>");
                out.println("<br>");
                for (Car cara : carsav) {
                    out.println("<div>");
                    out.println("Brand: " + cara.getBrand());
                    out.println("Model: " + cara.getModel());
                    out.println("Color: " + cara.getColor());
                    out.println("Licence Plate: " + cara.getLicencePlate());
                    out.println("</div><br>");
                }
                out.println("</div>");

                // Display rented cars
                out.println("<div>");
                out.println("<span style='text-decoration: underline; font-weight: bold;'>RENTED</span>");
                out.println("<br>");
                for (Car carr : carsre) {
                    out.println("<div>");
                    out.println("Brand: " + carr.getBrand());
                    out.println("Model: " + carr.getModel());
                    out.println("Color: " + carr.getColor());
                    out.println("Licence Plate: " + carr.getLicencePlate());
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

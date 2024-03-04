/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditBikesTable;
import database.tables.EditCarsTable;
import database.tables.EditMotorcyclesTable;
import database.tables.EditScootersTable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stsfa
 */
public class DamagedV extends HttpServlet {

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

        String vehicle = request.getParameter("vehicle");
        String lic = request.getParameter("licenceplate");
        int licencePlate = Integer.parseInt(lic);

        switch (vehicle.toLowerCase()) {
            case "car":
                EditCarsTable ect = new EditCarsTable();
                try {
                    ect.updatecarav(licencePlate);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DamagedV.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "motorcycle":
                EditMotorcyclesTable emt = new EditMotorcyclesTable();
                try {
                    emt.updatemotorav(licencePlate);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DamagedV.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "bike":
                EditBikesTable ebt = new EditBikesTable();
                try {
                    ebt.updatebikeav(licencePlate);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DamagedV.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case "scooter":
                EditScootersTable est = new EditScootersTable();
                try {
                    est.updatescooterav(licencePlate);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DamagedV.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

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

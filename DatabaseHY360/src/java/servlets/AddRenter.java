/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditRentersTable;
import database.tables.EditRentsTable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Rent;
import mainClasses.Renter;

/**
 *
 * @author stsfa
 */
public class AddRenter extends HttpServlet {

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
        doPost(request, response);
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
        try {
            EditRentersTable ert = new EditRentersTable();
            String firstName = request.getParameter("FirstName");
            String id = request.getParameter("id");
            String lastName = request.getParameter("LastName");
            String address = request.getParameter("add");
            String dateOfBirth = request.getParameter("Birthday");
            String licenseNumber = request.getParameter("LicenseNumber");
            String creditCard = request.getParameter("CreditCard");

            // Validate creditCard is a valid integer

            Renter renter = new Renter();
            renter.setFirstname(firstName);
            renter.setLastname(lastName);
            renter.setAddress(address);
            renter.setDateofbirth(dateOfBirth);
            renter.setLicencenumber(licenseNumber);
            renter.setCardnumber(Double.parseDouble(creditCard));

            ert.addNewRenter(renter);

            // Send a response to the client
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Renter added successfully!</h2>");
            out.println("</body></html>");
            request.setAttribute("firstname", firstName);
            request.setAttribute("lastname", lastName);
            request.setAttribute("vehicle_id", id);
            int id1 = Integer.parseInt(id);

            Rent rent = new Rent();
            rent.setFirstname(firstName);
            rent.setLastname(lastName);
            rent.setvehicle_id(id1);

            EditRentsTable lol = new EditRentsTable();
            lol.addNewRent(rent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/addRent");
            dispatcher.forward(request, response);

        } catch (NumberFormatException | ClassNotFoundException e) {
            // Log the exception and send an error response to the client
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding renter to the database: " + e.getMessage());
            // Log the exception
        }
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

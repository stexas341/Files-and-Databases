/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import database.tables.EditCarsTable;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainClasses.Car;

/**
 *
 * @author lympe
 */
public class AddCar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCar</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        response.setContentType("text/html;charset=UTF-8");

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String autonomy = request.getParameter("autonomy");
        String vehicletype = request.getParameter("vehicleType");
        String passengers = request.getParameter("passengers");
        String dailyrental = request.getParameter("dailyRentalCost");
        String insuranceCost = request.getParameter("insuranceCost");

        try {
            // Attempting to load JDBC driver
            System.out.println("HELLO IM HERE");
            Class.forName("com.mysql.cj.jdbc.Driver");

            double cost = Double.parseDouble(dailyrental);
            double insurance = Double.parseDouble(insuranceCost);

            int autonomyint = Integer.parseInt(autonomy);
            int passengersint = Integer.parseInt(passengers);

            EditCarsTable eut = new EditCarsTable();
            Car temp = new Car();

            temp.setBrand(brand);
            temp.setAvailable(1);
            temp.setColor(color);
            temp.setCost(cost);
            temp.setInsurance(insurance);
            temp.setModel(model);
            temp.setPassengers(passengersint);
            temp.setType(vehicletype);
            temp.setAutonomy(autonomyint);

            eut.addNewCar(temp);
            response.setStatus(200);

        } catch (ClassNotFoundException e) {
            System.out.println("THERE IS A PROBLEM HERE ");
            e.printStackTrace(); // Handle the exception appropriately
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dejah
 */
public class HomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        float elec = Float.parseFloat(request.getParameter("slider1"));
        float water = Float.parseFloat(request.getParameter("slider2"));
        float gas = Float.parseFloat(request.getParameter("slider3"));

        try
        {
          //loading drivers for mysql
          Class.forName("com.mysql.cj.jdbc.Driver");

          //creating connection with the database 
            Connection  con=DriverManager.getConnection
                      ("jdbc:mysql://localhost:3306/register","root","root");

          PreparedStatement ps=con.prepareStatement
                    ("insert into home values(?,?,?,?,?,?,?)");
          
          ps.setInt(1,0);
          ps.setString(2, "hi@gmail.com");
          ps.setFloat(3, elec);
          ps.setFloat(4, water);
          ps.setFloat(5, gas);
          
          double home_sum = 0.95 * elec + 0.4235 * gas + 0.0106* water;
          ps.setDouble(6, home_sum);
          
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
          ps.setTimestamp(7, date);
          
          int i = ps.executeUpdate();
          
          if(i>0)
          {
           response.sendRedirect("home.html");
          }
          
        }
        catch(ClassNotFoundException | SQLException se)
        {
            out.println(se);
        }

}}

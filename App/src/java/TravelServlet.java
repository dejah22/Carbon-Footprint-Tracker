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
public class TravelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
         
        String [] mod = request.getParameterValues("mode");
        String mode = "";
        for (String mode1 : mod) {
            mode = mode1;
        }
        
        float distance = Float.parseFloat(request.getParameter("slider1"));
        
        try
        {
          //loading drivers for mysql
          Class.forName("com.mysql.cj.jdbc.Driver");

          //creating connection with the database 
            Connection  con=DriverManager.getConnection
                      ("jdbc:mysql://localhost:3306/register","root","root");

          PreparedStatement ps=con.prepareStatement
                    ("insert into travel values(?,?,?,?,?,?)");
          
          ps.setInt(1,0);
          ps.setString(2, "hi@gmail.com");
          ps.setString(3, mode);       
          ps.setFloat(4, distance);
          
          double trav_sum=0;
          if("petcar".equals(mode))
            trav_sum = 0.192 * distance;
          
          if("diescar".equals(mode))
            trav_sum = 0.12 * distance;
          
          if("bus".equals(mode))
            trav_sum = 1.3 * distance;
          
          if("cycle".equals(mode))
            trav_sum = 0.021 * distance;
          
          if("air".equals(mode))
            trav_sum = 0.115 * distance;
          
          if("train".equals(mode))
            trav_sum = 0.041 * distance;
          
          ps.setDouble(5, trav_sum);
          
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
          ps.setTimestamp(6, date);
         
          int i = ps.executeUpdate();
          
          if(i>0)
          {
            response.sendRedirect("travel.html");
          }
          
        }
        catch(ClassNotFoundException | SQLException se)
        {
            out.println(se);
        }

}}

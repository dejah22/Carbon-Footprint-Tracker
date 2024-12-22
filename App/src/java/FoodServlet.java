import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FoodServlet extends HttpServlet 
{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
      {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String meat_s = request.getParameter("slider1");
        String grains_s = request.getParameter("slider2");
        String dairy_s = request.getParameter("slider3");
        String snacks_s = request.getParameter("slider4");
        String fruits_veggies_s = request.getParameter("slider5");
        
        
        float meat = Float.parseFloat(meat_s);
        float grains = Float.parseFloat(grains_s);
        float dairy = Float.parseFloat(dairy_s);
        float snacks = Float.parseFloat(snacks_s);
        float fruits_veggies = Float.parseFloat(fruits_veggies_s);
        
        float final_meat = (float) (meat/1000*10);
        float final_grains = (float) (grains/1000*1.6);
        float final_dairy = (float) (dairy/1000*2.4);
        float final_snacks = (float) (snacks/1000*2.8);
        float final_fruits_veggies = (float) (fruits_veggies/1000*2);
        
        float total = final_meat+final_grains+final_dairy+final_snacks+final_fruits_veggies;
        
        try
        {
          //loading drivers for mysql
          Class.forName("com.mysql.cj.jdbc.Driver");

          //creating connection with the database 
            Connection  con=DriverManager.getConnection
                      ("jdbc:mysql://localhost:3306/register","root","root");

          PreparedStatement ps=con.prepareStatement
                    ("insert into food values(?,?,?,?,?,?,?,?,?)");
          
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

          ps.setInt(1, 0);
          ps.setString(2, "hi@gmail.com");
          ps.setFloat(3, final_meat);
          ps.setFloat(4, final_grains);
          ps.setFloat(5, final_dairy);
          ps.setFloat(6, final_snacks);
          ps.setFloat(7, final_fruits_veggies);
          ps.setFloat(8, total);
          ps.setTimestamp(9, date);

          int i = ps.executeUpdate();
          
          if(i>0)
          {
            response.sendRedirect("food.html");
          }
          
        }
        catch(Exception se)
        {
          se.printStackTrace();
        }
	
      }
}

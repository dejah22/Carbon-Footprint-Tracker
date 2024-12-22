import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet 
{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
      {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String address = request.getParameter("address");
        String password = request.getParameter("password");

        String [] gen = request.getParameterValues("gender");
        String gender = "";

        for(int i = 0; i < gen.length; i++)
        {
          gender = gen[i];
        }

        try
        {
          //loading drivers for mysql
          Class.forName("com.mysql.cj.jdbc.Driver");

          //creating connection with the database 
            Connection  con=DriverManager.getConnection
                      ("jdbc:mysql://localhost:3306/register","root","root");

          PreparedStatement ps=con.prepareStatement
                    ("insert into user values(?,?,?,?,?,?,?,?)");

          ps.setString(1, name);
          ps.setString(2, email);
          ps.setString(3, age);
          ps.setString(4, gender);
          ps.setString(5, address);
          ps.setString(6, password);
          ps.setString(7, "check");
          ps.setString(8, "a001");

          int i = ps.executeUpdate();
          
          if(i>0)
          {
            out.println("<html>");
            out.println("<p>Registered Successfully</p>");
            out.println("<html>");
          }
          
        }
        catch(Exception se)
        {
          se.printStackTrace();
        }
	
      }
}

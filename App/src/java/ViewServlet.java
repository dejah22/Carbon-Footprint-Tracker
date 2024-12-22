import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ViewServlet extends HttpServlet 
{
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
      {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try
        {
          //loading drivers for mysql
          Class.forName("com.mysql.cj.jdbc.Driver");

          //creating connection with the database 
            Connection  con=DriverManager.getConnection
                      ("jdbc:mysql://localhost:3306/register","root","root");
            
          PreparedStatement ps=con.prepareStatement
                    ("insert into carbon_footprint values(?,?,?,?,?,?,?)");
          
          java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
          
          String query = "select * from carbon_footprint where day = (select curdate())";
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery(query);
          
          int count = 0;
          while(rs.next())
          {
              count++;
          }
          
          if(count!=0)
          {
                Statement stmt = (Statement) con.createStatement();
                String q = "delete from carbon_footprint where day = (select curdate())";
                stmt.executeUpdate(q);
          }
          
          CallableStatement cstmt1 = con.prepareCall("{? = call get_travel()}");
          cstmt1.registerOutParameter(1, java.sql.Types.FLOAT);
          cstmt1.execute();
          
          CallableStatement cstmt2 = con.prepareCall("{? = call get_home()}");
          cstmt2.registerOutParameter(1, java.sql.Types.FLOAT);
          cstmt2.execute();
          
          CallableStatement cstmt3 = con.prepareCall("{? = call get_food()}");
          cstmt3.registerOutParameter(1, java.sql.Types.FLOAT);
          cstmt3.execute();
          
          float travel = cstmt1.getFloat(1);
          float home = cstmt2.getFloat(1);
          float food = cstmt3.getFloat(1);
          
          float total = travel+home+food;
          
          ps.setString(1, "hi@gmail.com");
          ps.setTimestamp(2, date);
          ps.setFloat(3, travel);
          ps.setFloat(4, home);
          ps.setFloat(5, food);
          ps.setFloat(6, total);
          ps.setString(7, "check");

          int i = ps.executeUpdate();
          
          if(i>0)
          {
            String q1 = "select * from carbon_footprint where day = (select curdate())";
            Statement stt = con.createStatement();
            ResultSet rss = st.executeQuery(query);
            out.println("<html>");
            out.println("<head><link rel = \"stylesheet\" href=\"statstyle.css\"></head");
            while(rss.next())
            {
               out.println("<body>\n" +
"	<nav>\n" +
 "               <a href=\"index.html\">Back to HomePage</a>" +
"		<a href=\"home.html\">Home</a>\n" +
"		<a href=\"travel.html\">Travel</a>\n" +
"		<a href=\"food.html\">Food</a>\n" +
"	</nav>\n" +
"	<div class=\"emerge\">\n" +
"    <div class=\"box\">\n" +
"\n" +
"		<b><p style=\"font-size: 30px;\">CARBON FOOTPRINT </p></b>\n");
                out.println("<p>Travel Carbon Footprint: " + rss.getFloat("travel_sum") + " kilogram carbon equivalent</p>");
               out.println("<p>Home Carbon Footprint: " + rss.getFloat("home_sum") + " kilogram carbon equivalent</p>"); 
               out.println("<p>Food Carbon Footprint: " + rss.getFloat("food_sum") + " kilogram carbon equivalent</p>"); 
               out.println("<br><h1>Overall Carbon Footprint: " + rss.getFloat("footprint") + " kilogram carbon equivalent</h1>"); 
               out.println("	</div>\n" +
"</div>\n" +
"</div>\n" +
"</body>");
            }
            out.println("<html>");
          }
          
        }
        catch(Exception se)
        {
          se.printStackTrace();
        }
	
      }
}

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmLoginServlet extends HttpServlet {

    private final String url = "jdbc:mysql://localhost:3306/register";
    private final String username = "root";
    private final String pwd = "root";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String auth_id = request.getParameter("auth_id");
        String password = request.getParameter("password");

        try (Connection connection = DriverManager.getConnection(url, username, pwd)) {

            String sql = "SELECT * FROM authority WHERE auth_id = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, auth_id);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                response.sendRedirect("home.html");
            } else {
                response.sendRedirect("admlogin.html?error=1");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}


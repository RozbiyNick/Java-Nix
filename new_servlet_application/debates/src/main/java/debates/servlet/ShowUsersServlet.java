package debates.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

public class ShowUsersServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try{
            String url = "jdbc:mysql://localhost:3306/debates";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
                writer.println("<p>Connection to debates in MySQL Server succesfull!</p>");
                writer.println("<a href=\"./\">Main page</a>");
            }
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
    }
}
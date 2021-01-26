import MyClasses.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"MyServlet.css\">");
            writer.println("<title>Organized Addresses</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<div class=\"header\">");
            writer.println("<h1>Follow the route for directions</h1>");
            writer.println("</div>");
            writer.println("<p id = \"addresses\">");
            //------------------------------------------------
            CreateRoute newRoute = new CreateRoute();
            //-------------------------------------------------
            // Print the open to the rows and columns and add them onto the string before
            // Printing to the page
            writer.println("<div class = \"row\">");
            writer.println("<div class = \"col-3 menu\">");
            writer.println("<ul>");
            // Right before here the temp holds the organized string to display to the browser the addresses
            // String uses a "|" as a delimiter

            for(String s : newRoute.organizedData){
                s = "<li>" + s + "</li>";
                writer.println(s);
            }
            writer.println("</ul>");
            writer.println("</div>");
            writer.println("<div class=\"col-9\">");
                // Data coming from the buttons would go here
            writer.println("<h1>Dynamically update directions based on address clicked</h1>");
            writer.println("<p>This functionality will be coming in verion 2 along with menu updates" +
                    " and a full UI overhaul.</p>");
            writer.println("</div>");
            writer.println("</div>");
            writer.println("</p>");

            writer.println("</body>");
            writer.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

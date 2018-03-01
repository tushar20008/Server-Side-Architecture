package svlt;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     processRequest(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
     processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html;charset=UTF-8");
    
    if(request.getParameter("bigname") == null || request.getParameter("bigname").length() == 0)
        response.sendError(response.SC_NOT_FOUND,"Name is missing.");
    
    PrintWriter out = response.getWriter();
    
    out.println("<html>");
    out.println("<head>");
    out.println("<title>CS4280 T05 - Java Servlet</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>CS4280 T05 - Java Servlet</h1>");
    out.println("<h3>I used " + request.getMethod() +" method with "+ request.getHeader("User-Agent") +"</h3>");
    out.println("<p>My name is " + ((request.getParameter("salutation") != null && "F".equalsIgnoreCase(request.getParameter("salutation")) != false)  ? "Miss" : "Mr") + " " + request.getParameter("bigname") + ".</p>");
    out.println("<p>I am" + ((request.getParameter("rich") != null && "on".equalsIgnoreCase(request.getParameter("rich"))) ? " " : " <em>not</em> ") + "rich and famous!</p>");
    out.println("</body>");
    out.println("</html>");
  }
}


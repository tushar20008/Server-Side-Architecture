package cs4280;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegURLServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = null, email = null, pass = null;
                        name = request.getParameter("name");
                email = request.getParameter("email");
                pass = request.getParameter("password");
        try {
            out.println("<html><head>");
            out.println("<title>City Company - Membership System</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Registration</h1>");
            out.println("<form action='" + request.getRequestURI() + "' method='POST' style='width:600px;'>");
            out.println("<fieldset>");
            String pageNum = request.getParameter("page");
            String error = "Error.";
            if (pageNum != null && pageNum.equals("2") && (name == null || email == null || pass == null)
            //  && (if name OR email OR password is empty)
            ) {
                // display error message and Page 1 instead of Page 2
                out.println("<p style='color: red'>" + error + "</p>");
                pageNum = "1";
            }
            if (pageNum != null && pageNum.equals("2")) {
                // Page 2 Layout
                out.println("<legend>Please fill in the form below</legend>");
                out.println("<input type='hidden' name='page' value='3' />");
                out.println("<p>Education Level<br/><select name='level' style='width:100%;'>");
                out.println("<option>Secondary School</option>");
                out.println("<option>College</option>");
                out.println("<option>Undergraduate</option>");
                out.println("<option>Postgraduate</option>");
                out.println("<option>Other</option>");
                out.println("</select></p>");
                out.println("<p>Year of Birth (YYYY)<br/><input type='text' name='yob' style='width:100%;' /></p>");
                out.println("<p>Self Description<br/><textarea name='desc' rows='5' style='width:100%;'></textarea></p>");
                out.println("<p><input type='submit' value='Continue' /></p>");
                out.println("<input type='hidden' name='name' value='"+name+"' />");
                out.println("<input type='hidden' name='email' value='"+email+"' />");
                out.println("<input type='hidden' name='password' value='"+pass+"' />");
                
            }
            else if (pageNum != null && pageNum.equals("3")) {
                // Page 3 Layout - Summary
                out.println("<legend>Completed</legend>");
                out.println("<p><b>Thank you for your registration.</b></p><br />");
                out.println("<p><b>Name : "+name+"</b></p><br />");
                out.println("<p><b>Email : "+email+"</b></p><br />");
                out.println("<p><b>Password :"+pass+"</b></p><br />");
                out.println("<p><b>Education Level :"+request.getParameter("level")+"</b></p><br />");
                out.println("<p><b>Year of Birth :"+request.getParameter("yob")+"</b></p><br />");
                out.println("<p><b>Self Description :"+request.getParameter("desc")+"</b></p><br />");
            }
            else {
                // Page 1 Layout
                out.println("<legend>Please fill in the form below</legend>");
                out.println("<input type='hidden' name='page' value='2' />");
                out.println("<p>Name *<br/><input type='text' name='name' style='width:100%;' value='' /></p>");
                out.println("<p>Email *<br/><input type='text' name='email' style='width:100%;' value='' /></p>");
                out.println("<p>Password *<br/><input type='text' name='password' style='width:100%;' value='' /></p>");
                out.println("<p><input type='submit' value='Continue' /></p>");
            }
            out.println("</fieldset>");
            out.println("</form>");
            out.println("</body></html>");
        }
        catch (NullPointerException e) {
            // return a Bad Request (400) Error
            response.sendError(response.SC_BAD_REQUEST, e.getMessage());
        }
        finally {
            out.close();
        }
		
		
    }
	
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
	
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
	
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
	
}

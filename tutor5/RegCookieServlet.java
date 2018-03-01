package cs4280;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegCookieServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Cookie name = null, email = null, pass = null;
        
        
         name = new Cookie("name", request.getParameter("name"));
             
            email = new Cookie("email", request.getParameter("email"));
                    
                    pass = new Cookie("pass", request.getParameter("password"));
                 
               
                response.addCookie(name);
                response.addCookie(email);
                response.addCookie(pass);
        
        
        try {
            StringBuffer html = new StringBuffer("");
            html.append("<html><head>");
            html.append("<title>City Company - Membership System</title>");
            html.append("</head>");
            html.append("<body>");
            html.append("<h1>Registration</h1>");
            html.append("<form action='" + request.getRequestURI() + "' method='POST' style='width:600px;'>");
            html.append("<fieldset>");
            String pageNum = request.getParameter("page");
            String error = "Error.";
            
            Cookie[] cookies = request.getCookies();
            Cookie getName = null, getEmail = null, getPass = null;
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("name")) {
                        getName = cookies[i];
                    }
                     if (cookies[i].getName().equals("email")) {
                        getEmail = cookies[i];
                    }
                      if (cookies[i].getName().equals("pass")) {
                        getPass = cookies[i];
                    }
                }
            }
            if (pageNum != null && pageNum.equals("2") && (getName == null || getEmail == null || getPass == null)
            //  && (if name OR email OR password is empty)
            ) {
                // display error message and Page 1 instead of Page 2
                html.append("<p style='color: red'>" + error + "</p>");
                pageNum = "1";
            }
            if (pageNum != null && pageNum.equals("2")) {
                // save Page 1 info into cookies
                // response.addCookie ( ... )
				
                // Page 2 Layout
                html.append("<legend>Please fill in the form below</legend>");
                html.append("<input type='hidden' name='page' value='3' />");
                html.append("<p>Education Level<br/><select name='level' style='width:100%;'>");
                html.append("<option>Secondary School</option>");
                html.append("<option>College</option>");
                html.append("<option>Undergraduate</option>");
                html.append("<option>Postgraduate</option>");
                html.append("<option>Other</option>");
                html.append("</select></p>");
                html.append("<p>Year of Birth (YYYY)<br/><input type='text' name='yob' style='width:100%;' /></p>");
                html.append("<p>Self Description<br/><textarea name='desc' rows='5' style='width:100%;'></textarea></p>");
                html.append("<p><input type='submit' value='Continue' /></p>");
            }
            else if (pageNum != null && pageNum.equals("3")) {
                // save page 2 info into cookies
				
                // Page 3 Layout - Summary
                // display the form information by obtaining the values from cookies
                html.append("<legend>Completed</legend>");
                html.append("<p><b>Thank you for your registration.</b></p><br />");
                html.append("<p><b>Name : "+getName.getValue()+"</b></p><br />");
                html.append("<p><b>Email : "+getEmail.getValue()+"</b></p><br />");
                html.append("<p><b>Password :"+getPass.getValue()+"</b></p><br />");
                html.append("<p><b>Education Level :"+request.getParameter("level")+"</b></p><br />");
                html.append("<p><b>Year of Birth :"+request.getParameter("yob")+"</b></p><br />");
                html.append("<p><b>Self Description :"+request.getParameter("desc")+"</b></p><br />");
            }
            else {
                // Page 1 Layout)
                html.append("<legend>Please fill in the form below</legend>");
                html.append("<input type='hidden' name='page' value='2' />");
                html.append("<p>Name *<br/><input type='text' name='name' style='width:100%;' value='' /></p>");
                html.append("<p>Email *<br/><input type='text' name='email' style='width:100%;' value='' /></p>");
                html.append("<p>Password *<br/><input type='text' name='password' style='width:100%;' value='' /></p>");
                html.append("<p><input type='submit' value='Continue' /></p>");
                
            }
            html.append("</fieldset>");
            html.append("</form>");
            html.append("</body></html>");
			
            out.print (html.toString());
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

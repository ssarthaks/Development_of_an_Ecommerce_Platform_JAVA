package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Feedback;
import service.FeedbackDao;


/**
 * Servlet implementation class ContactUs
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ContactUs" })
public class ContactUs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.getRequestDispatcher(ViewPages.CONTACT_US).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve form parameters
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String message = request.getParameter("message");
	    
	    // Create a Feedback instance with the submitted data
	    Feedback feedback = new Feedback(name, email, message);
	    
	    try {
	        // Insert feedback into the database using FeedbackDao
	        boolean success = FeedbackDao.insertFeedback(feedback);
	        if (success) {
	            // Send a JavaScript alert message
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<script type=\"text/javascript\">");
	            out.println("alert('Thank you for your feedback!');");
	            out.println("window.location.href = '" + request.getContextPath() + "/ViewIndex';");
	            out.println("</script>");
	        } else {
	            // Handle failure case
	            // You can redirect to an error page or do something else
	            response.sendRedirect(request.getContextPath() + ViewPages.NO_PRODUCT_FOUND);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        throw new ServletException("Database error", ex);
	    }
	}



}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserDao;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateProfile" })
public class UpdateUser extends HttpServlet {
private static final long serialVersionUID = 1L;
       

private UserDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
request.getRequestDispatcher(ViewPages.UPDATE_PROFILE).forward(request, response);
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String oldEmail = (String) request.getSession().getAttribute("email");
    String newEmail = request.getParameter("email");

    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");
    String username = request.getParameter("username");
    long phone = Long.parseLong(request.getParameter("phone"));
    String address = request.getParameter("address");

    User updatedUser = new User();
    updatedUser.setFirstname(firstname);
    updatedUser.setLastname(lastname);
    updatedUser.setUsername(username);
    updatedUser.setEmail(newEmail);
    updatedUser.setPhone(phone);
    updatedUser.setAddress(address);

    boolean success = userDao.updateUser(updatedUser, oldEmail);

    if (success) {
        // Update the user object in the session with new details
        request.getSession().setAttribute("user", updatedUser);
        request.getSession().setAttribute("email", newEmail); // Update the email in session if it's changed
        response.sendRedirect("Profile"); // Ensure "Profile" points to profile.jsp or the URL mapping for displaying the profile
    } else {
        response.getWriter().println("Failed to update user information.");
    }
}

}
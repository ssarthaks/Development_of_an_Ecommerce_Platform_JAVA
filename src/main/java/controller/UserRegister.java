package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserDao;
import utils.PasswordHash;

/**
 * Servlet implementation class StudentRegister
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class UserRegister extends HttpServlet {
private UserDao userDao;
private static final long serialVersionUID = 1L;
       
@Override
public void init(ServletConfig config) throws ServletException {
// TODO Auto-generated method stub
super.init(config);
userDao=new UserDao();
}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
request.getRequestDispatcher(ViewPages.REGISTER_PAGE).forward(request, response);
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
String firstname = request.getParameter("firstname");
String lastname = request.getParameter("lastname");
String username = request.getParameter("username");
String email =request.getParameter("email");
long phone = Long.parseLong(request.getParameter("phone"));
String address =request.getParameter("address");
String password =request.getParameter("password");
String retypepassword =request.getParameter("retypepassword");


if(!password.equals(retypepassword))
{
request.setAttribute("error", "not matched");
request.setAttribute("firstname", firstname);
request.getRequestDispatcher("WEB-INF/view/register.jsp").forward(request, response);

}
User user = new User();
user.setFirstname(firstname);
user.setLastname(lastname);
user.setUsername(username);
user.setEmail(email);
user.setPhone(phone);
user.setAddress(address);
user.setPassword(PasswordHash.getPasswordHash(password));

try {
boolean isSuccess=userDao.saveUser(user);
System.out.println(isSuccess);
if (isSuccess)
{
request.getRequestDispatcher("login").forward(request, response);;
}
else
{
request.setAttribute("error1", "Username or Email or PhoneNumber already Taken");
request.getRequestDispatcher(ViewPages.REGISTER_PAGE).forward(request, response);
}
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

}
}
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ViewPages;
import model.User;
import service.UserDao;

@WebFilter(urlPatterns = {"/AdminDashboard", "/AdminPanel", "/AdminProduct", "/AdminUser", "/addProduct","/AdminOrder"})
public class AdminFilter implements Filter {
    
    public void init(FilterConfig fConfig) throws ServletException {
        // Initialization code, if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (isAdminAuthenticated(httpRequest)) {
            chain.doFilter(request, response);
        } else {
            // Redirect to custom error page with 404 status
            httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            httpRequest.getRequestDispatcher(ViewPages.NO_PRODUCT_FOUND).forward(request, response);
        }
    }

    public void destroy() {
        // Cleanup code, if needed
    }

    private boolean isAdminAuthenticated(HttpServletRequest request) {
        String email = (String) request.getSession().getAttribute("email");
        if (email == null || email.isEmpty()) {
            return false;
        }
        UserDao userDao = new UserDao();
        User user = userDao.getUserByEmail(email);
        
        int role = user.getRole();
        return role == 1;
    }
}

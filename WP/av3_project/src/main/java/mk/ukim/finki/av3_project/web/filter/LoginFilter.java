package mk.ukim.finki.av3_project.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.av3_project.model.User;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = {"/*"},
         dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD},
        initParams = @WebInitParam(name = "ignore-path", value = "/login"))
public class LoginFilter implements Filter {

    private String ignorePath;
    //private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath=filterConfig.getInitParameter("ignore-path");
        //registerPath=filterConfig.getInitParameter("register-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //System.out.println("FIlter Activated");
        User user= (User) req.getSession().getAttribute("user");

        //This is the url path the user tried to access
        String path=req.geServletPath();

        //if(path.startsWith(ignorePath) || path.startsWith(registerPath) || user!=null){
        if(path.startsWith(ignorePath) || user!=null){
            //Allow access if the path is login or register or if a user has logged in
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            //Redirect to login page if the user hasn't logged in and is trying
            //to access restricted pages
            resp.sendRedirect("/login");
        }

    }
}

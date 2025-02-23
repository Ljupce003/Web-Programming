package mk.ukim.finki.av3_project.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.av3_project.model.User;

import java.io.IOException;
/*

@WebFilter(filterName = "CategoryViewCountFIlter",urlPatterns = "/category",
        dispatcherTypes = {DispatcherType.REQUEST,DispatcherType.FORWARD},
        initParams = @WebInitParam(name = "safe_path", value = "/login"))
public class CategoryViewCountFIlter implements Filter {

    private String ignorePath;
    //private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath=filterConfig.getInitParameter("safe_path");
        //registerPath=filterConfig.getInitParameter("register-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user= (User) req.getSession().getAttribute("user");

        //This is the url path the user tried to access
        String path=req.getServletPath();

        //if(path.startsWith(ignorePath) || path.startsWith(registerPath) || user!=null){
        if(path.startsWith(ignorePath) || user!=null){
            //Allow access if the path is login or register or if a user has logged in

            ServletContext context=req.getServletContext();
            if(req.getMethod().equalsIgnoreCase("GET")){
                Integer count=(Integer) context.getAttribute("userViews");
                count++;
                context.setAttribute("userViews",count);
            }

            filterChain.doFilter(servletRequest,servletResponse);
        }
        else {
            //Redirect to login page if the user hasn't logged in and is trying
            //to access restricted pages
            resp.sendRedirect("/login");
        }

    }
}

 */
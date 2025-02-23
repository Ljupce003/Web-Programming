package mk.ukim.finki.av3_project.web.servlet;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.av3_project.model.User;
import mk.ukim.finki.av3_project.services.AuthorizeService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login",""})
public class LoginServlet extends HttpServlet {
    private AuthorizeService authorizeService;
    private SpringTemplateEngine springTemplateEngine;

    public LoginServlet(AuthorizeService authorizeService, SpringTemplateEngine springTemplateEngine) {
        this.authorizeService = authorizeService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange exchange= JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context=new WebContext(exchange);
        //context variables here

        springTemplateEngine.process("login.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange=JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);

        WebContext context=new WebContext(webExchange);

        String username=req.getParameter("username");
        String password=req.getParameter("password");

        User user=null;
        try {
            user=authorizeService.login(username,password);
        }catch (RuntimeException e){

            context.setVariable("hasError",true);
            context.setVariable("error",e.getMessage());

            springTemplateEngine.process("login.html",context,resp.getWriter());

        }

        if(user!=null){
            //we add this logged user to the session to be remembered later
            req.getSession().setAttribute("user",user);
            resp.sendRedirect("/category");
        }



    }
}

package mk.ukim.finki.wp_testing.web.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_testing.services.CategoryService;
import mk.ukim.finki.wp_testing.services.CategoryServiceIMPL.CategoryServiceImplementation;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = {"/listo"})
public class ListingServletORGANISED extends HttpServlet {

    private final CategoryService categoryService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListingServletORGANISED(CategoryService categoryService, SpringTemplateEngine springTemplateEngine) {
        this.categoryService = categoryService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress",req.getRemoteAddr());
        context.setVariable("movieList",categoryService.listCategories());
        context.setVariable("user_agent",req.getHeader("user-agent"));

        springTemplateEngine.process("frontend.html",context,resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String description=req.getParameter("year");

        categoryService.create(name,description);

        resp.sendRedirect("/listo");
    }
}

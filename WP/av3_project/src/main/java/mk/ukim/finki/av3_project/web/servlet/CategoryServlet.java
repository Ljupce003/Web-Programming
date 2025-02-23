package mk.ukim.finki.av3_project.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.av3_project.services.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;
    private final SpringTemplateEngine springTemplateEngine;

    public CategoryServlet(CategoryService categoryService, SpringTemplateEngine springTemplateEngine) {
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

        String err=(String) req.getSession().getAttribute("error");


        if(err!=null && !err.isEmpty()){
            req.getSession().removeAttribute("error");
            context.setVariable("ErrorAppeared",true);
            context.setVariable("theError",err);
        }


        Integer userViews= (Integer) req.getServletContext().getAttribute("userViews");
        context.setVariable("totalViews",userViews);

        Long seconds=(long) req.getServletContext().getAttribute("time");
        context.setVariable("time",seconds);


        Boolean turn_red= (Boolean) req.getServletContext().getAttribute("turn_red");
        context.setVariable("turn_red",turn_red);

        springTemplateEngine.process("frontend.html",context,resp.getWriter());

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String description=req.getParameter("year");

        try {
            categoryService.create(name,description);
        }catch (IllegalArgumentException e){
            //req.setAttribute("error","Illegal Argument man");
            req.getSession().setAttribute("error","Illegal Argument man");
        }

        resp.sendRedirect("/category");

        //req.getRequestDispatcher("/category").forward(req,resp);
    }
}

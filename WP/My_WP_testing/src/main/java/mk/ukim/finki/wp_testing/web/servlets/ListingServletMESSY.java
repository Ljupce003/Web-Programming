package mk.ukim.finki.wp_testing.web.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_testing.model.Movie;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet( name = "ListingServlet" , urlPatterns = {"/listmessy"})
public class ListingServletMESSY extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    private List<Movie> movieList;

    public ListingServletMESSY(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        this.movieList=new ArrayList<>();
        this.movieList.add(new Movie("Titanic",1997));
    }

    public void addMovie(String name,Integer year){
        if(name==null || name.isEmpty() || year==null )return;

        Movie newMovie=new Movie(name,year);
        this.movieList.add(newMovie);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("movieList",this.movieList);
        context.setVariable("user_agent",req.getHeader("user-agent"));
        context.setVariable("ipAddress", req.getRemoteAddr());

        this.springTemplateEngine.process("frontend.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String y=req.getParameter("year");
        if(y==null || y.isEmpty()){resp.sendRedirect("/list");return;}
        Integer year=Integer.parseInt(y);

        addMovie(name,year);

        resp.sendRedirect("/listmessy");
    }
}

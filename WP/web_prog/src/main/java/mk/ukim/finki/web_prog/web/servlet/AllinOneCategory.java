package mk.ukim.finki.web_prog.web.servlet;


import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AllinOneCategory",urlPatterns = {"/allin"})
public class AllinOneCategory extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;

    @Getter
    @Setter
    @Data
    @AllArgsConstructor
    public static class Categorija {

        private String name;
        private String description;
    }

    private List<Categorija> categoryList=null;


    public AllinOneCategory(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }


    public void addCategory(String name,String desc){

        if(name==null || name.isEmpty() || desc ==null || desc.isEmpty()) return;
        Categorija newCategory= new Categorija(name, desc);
        categoryList.add(newCategory);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);  //NE TREBA DA SE BRISE VOA OTI FRLA NULL POINTER EXCEPTION

        this.categoryList=new ArrayList<>();

        addCategory("Book","Silmarillion");
        addCategory("Movie","Avengers Endgame");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("userAgent", req.getHeader("user-agent"));
        context.setVariable("categories", this.categoryList);

        springTemplateEngine.process("categories.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        addCategory(name, description);

        resp.sendRedirect("/allin");

    }
}

package mk.ukim.finki.wp_testing.web.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

@WebServlet( name="SimpleHttp",urlPatterns = {"/home"})
public class SimpleHttp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> headers = new TreeMap<>();

        //gi zimam site headers from the request i posle samo gi prikazuvam na page
        req.getHeaderNames().asIterator().forEachRemaining(h -> headers.put(h, req.getHeader(h).toLowerCase()));

        PrintWriter pr = resp.getWriter();
        pr.print("<html><head></head><body>");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            pr.print("H: "+entry.getKey()+"  - DESC: "+entry.getValue()+" <br>");
        }

        //resp.setHeader("Accept","/html");
        pr.print("</body></html>");
        System.out.println(headers.get("accept"));
        pr.flush();

        System.out.println(resp.getHeaderNames());


    }
}

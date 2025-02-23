package mk.ukim.finki.web_prog.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "HelloServlet" , urlPatterns = {"","/home"})
public class HelloServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getServerName();
        String address=req.getRemoteAddr();

        PrintWriter pr=resp.getWriter();

        pr.printf("<html><head></head><body><h1>Hello %s %s</h1></body></html>\n", name, address);
        pr.flush();
    }
}

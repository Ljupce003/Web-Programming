package mk.ukim.finki.av3_project.web.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.av3_project.model.User;
import org.springframework.web.context.request.RequestContextListener;

import java.net.http.HttpRequest;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

@WebListener()
public class UserAccessListener extends RequestContextListener {

    LocalTime last_access_time;
    public UserAccessListener() {
        this.last_access_time=null;
    }

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        HttpServletRequest req=(HttpServletRequest) requestEvent.getServletRequest();


        User user = (User) req.getSession().getAttribute("user");
        if(req.getMethod().equalsIgnoreCase("GET") && req.getRequestURI().equals("/category") && user!=null){

            boolean good=true;

            Long seconds= (long) 0;

            LocalTime curr_time=LocalTime.now();
            if(last_access_time==null){
                last_access_time=curr_time;
            }
            else {
                seconds= Duration.between(last_access_time,curr_time).getSeconds();
                requestEvent.getServletContext().setAttribute("time",seconds);
                if(seconds>50){
                    req.getSession().removeAttribute("user");
                    good=false;
                    last_access_time=null;
                }
            }
            if(good){
                if(seconds>10)requestEvent.getServletContext().setAttribute("turn_red",true);
                requestEvent.getServletContext().setAttribute("time",seconds);

                last_access_time=curr_time;
                Integer views=(Integer) requestEvent.getServletContext().getAttribute("userViews");
                views++;

                requestEvent.getServletContext().setAttribute("userViews",views);
            }



        }
        super.requestInitialized(requestEvent);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        super.requestDestroyed(requestEvent);
    }
}

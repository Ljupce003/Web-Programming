package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EventBookingController {
    private final EventBookingService eventBookingService;
    private final EventService eventService;
    private final UserService userService;
    public EventBookingController(EventBookingService eventBookingService, EventService eventService, UserService userService) {
        this.eventBookingService = eventBookingService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/eventBooking")
    public String getConfirmationPage(HttpServletRequest servletRequest, HttpSession session, Model model){

        String u_name= servletRequest.getRemoteUser();
        String title= (String) session.getAttribute("title");
        String numTickets= (String) session.getAttribute("numTickets");


        EventBooking newBooking=this.eventBookingService.placeBooking(title,u_name,"Orce Nikolov",Long.parseLong(numTickets));
        if(newBooking!=null){
            this.eventService.addBooking(title,newBooking);
            Optional<User> optionalUser=this.userService.findUserByName(u_name);
            if(optionalUser.isPresent()){
                User user=optionalUser.get();
                this.userService.addUserBooking(user.getId(),newBooking);
            }
        }


        model.addAttribute("user_name",u_name);
        model.addAttribute("title",title);
        model.addAttribute("ticket_num",numTickets);
        model.addAttribute("ip_address",servletRequest.getRemoteAddr());

        return "bookingConfirmation";
    }

    @PostMapping("/eventBooking")
    public String postToConfirmationPage(HttpServletRequest servletRequest, HttpSession session){

        String title= servletRequest.getParameter("radio_b");
        String numTickets= servletRequest.getParameter("numTickets");
        session.setAttribute("title",title);
        session.setAttribute("numTickets",numTickets);

        return "redirect:/eventBooking";
    }

}

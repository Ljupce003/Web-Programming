package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class EventListController {
    private final EventService eventService;

    public EventListController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/book")
    public String getEventSelectionPage(HttpServletRequest req, HttpSession session, Model model){

        model.addAttribute("list",eventService.listAll());

        String intError=req.getParameter("intError");
        if(intError!=null){
            model.addAttribute("intError",intError);
        }

        Object results=session.getAttribute("results");
        if(results!=null){
            model.addAttribute("results",results);
            model.addAttribute("list_available",true);

        }
        model.addAttribute("sess_id",session.getId());
        model.addAttribute("sess_t",session.getCreationTime());

        return "BookEvent";
    }

    @PostMapping("/book")
    public String PostToEventSelectionPage(@RequestParam String text,
                                           @RequestParam Double rating,
                                           HttpSession session){

        List<Event> results=this.eventService.searchEventsAndByRating(text,rating);
        session.setAttribute("results",results);

        return "redirect:/book";
    }

    @PostMapping("/book/upload")
    public String uploadFile(@RequestPart("my_file") MultipartFile file) throws IOException {


        file.transferTo(new File(System.getProperty("user.dir"),file.getName()));

        return "redirect:/book";
    }




}


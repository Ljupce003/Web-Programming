package mk.ukim.finki.mk.lab.web.controller;


import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.service.AuthService;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;

    }

    @GetMapping
    public String getLoginPage(HttpSession session,Model model) {


        Object msg =session.getAttribute("error");
        if(msg!=null){
            model.addAttribute("hasError",true);
            model.addAttribute("error",msg.toString());
            session.removeAttribute("error");
        }

        // Return the name of the Thymeleaf template that will be used to render the login page
        return "login";
    }
    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            user = authService.login(username, password);
            request.getSession().setAttribute("user", user);
            // Redirect to the home page
            return "redirect:/home";
        } catch (RuntimeException ex) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", ex.getMessage());
            return "login";
        }
    }

}

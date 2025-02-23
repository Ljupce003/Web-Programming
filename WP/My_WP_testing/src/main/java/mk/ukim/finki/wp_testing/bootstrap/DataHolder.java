package mk.ukim.finki.wp_testing.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp_testing.model.Category;
import mk.ukim.finki.wp_testing.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories=null;
    public static List<User> users = null;


    //@PostConstruct
    public void init(){
        System.out.println("Dataholder initalised");
        categories=new ArrayList<>();
        categories.add(new Category("Movie","Titanic 1997"));
        categories.add(new Category("Book","Harry Potter 1"));

        users=new ArrayList<>();
        users.add(new User("Ljupcho","Angelovski","Ljupcho","123"));
        users.add(new User("Bojana","Jovancheva","Bojana","456"));

    }
}

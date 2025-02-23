package mk.ukim.finki.av3_project.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.ukim.finki.av3_project.model.Category;
import mk.ukim.finki.av3_project.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<User> users=null;
    public static List<Category> categories=null;

    @PostConstruct
    public void init(){
        //System.out.println("Dataholder initialised");
        categories=new ArrayList<>();
        categories.add(new Category("Movie","Titanic 1997"));
        categories.add(new Category("Book","Harry Potter 1"));


        users=new ArrayList<>();
        users.add(new User("Ljupcho","Angelovski","Ljupcho","123"));
        users.add(new User("Bojana","Jovancheva","Bojana","456"));

    }
}

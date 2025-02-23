package mk.ukim.finki.av3_project.services;

import mk.ukim.finki.av3_project.model.User;

public interface AuthorizeService {
    User login(String username,String password);

    User register(String name,String surname,String username,String password,String repeatPassword);
}

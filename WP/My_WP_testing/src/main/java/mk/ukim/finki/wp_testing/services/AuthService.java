package mk.ukim.finki.wp_testing.services;

import mk.ukim.finki.wp_testing.model.User;

public interface AuthService {

    User login(String username, String password);

    User register(String username, String password, String repeatPassword, String name, String surname);

}


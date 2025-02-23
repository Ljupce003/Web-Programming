package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.User;

public interface AuthService {

    User login(String username,String password) throws RuntimeException;
}

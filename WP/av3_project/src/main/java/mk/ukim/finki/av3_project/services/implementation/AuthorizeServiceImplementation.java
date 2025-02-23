package mk.ukim.finki.av3_project.services.implementation;

import mk.ukim.finki.av3_project.bootstrap.DataHolder;
import mk.ukim.finki.av3_project.model.User;
import mk.ukim.finki.av3_project.model.exceptions.IllegalArgumentsException;
import mk.ukim.finki.av3_project.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.av3_project.model.exceptions.PasswordMismatchException;
import mk.ukim.finki.av3_project.repository.LocalUsersRepository;
import mk.ukim.finki.av3_project.services.AuthorizeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizeServiceImplementation implements AuthorizeService {

    private final LocalUsersRepository usersRepository;

    public AuthorizeServiceImplementation(LocalUsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty()){
            throw new IllegalArgumentsException();
        }

        Optional<User> optional=usersRepository.findByUsernameAndPassword(username,password);
        if(optional.isPresent()){
            return optional.get();
        }
        else {
            throw new InvalidCredentialsException();
        }

    }

    @Override
    public User register(String name, String surname, String username, String password, String repeatPassword) {

        if(name==null || name.isEmpty() || surname==null || surname.isEmpty() || username==null || username.isEmpty() || password==null || password.isEmpty() || repeatPassword==null || repeatPassword.isEmpty()){
            throw new IllegalArgumentsException();
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordMismatchException();
        }

        User newUser=new User(name,surname,username,password);
        usersRepository.saveOrUpdate(newUser);
        return newUser;
    }
}

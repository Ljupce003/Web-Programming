package mk.ukim.finki.wp_testing.services.CategoryServiceIMPL;

import mk.ukim.finki.wp_testing.model.User;
import mk.ukim.finki.wp_testing.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp_testing.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_testing.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp_testing.repository.InMemoryUserRepository;
import mk.ukim.finki.wp_testing.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    // Inject the InMemoryUserRepository
    private final InMemoryUserRepository userRepository;

    public AuthServiceImpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User login(String username, String password) {
        // Check if the username and password are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        // Check if the username, password, name and surname are not null or empty
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        // Check if the password and the repeated password are the same
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }


        return userRepository.saveOrUpdate(new User(username, password, name, surname));
    }
}


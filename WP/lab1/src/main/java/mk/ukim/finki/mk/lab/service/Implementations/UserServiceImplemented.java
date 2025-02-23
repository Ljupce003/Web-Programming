package mk.ukim.finki.mk.lab.service.Implementations;

import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.User;
import mk.ukim.finki.mk.lab.model.enums.Role;
import mk.ukim.finki.mk.lab.repository.jpa.UserRepository;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplemented implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplemented(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }


    @Profile("servlet")
    @Override
    public Optional<User> addUser(String username) {
        User user=new User(username);
        if(this.userRepository.getUserByName(username).isEmpty()){
            return Optional.of(this.userRepository.save(user));
        }
        else return Optional.empty();
    }

    @Override
    public Optional<User> addUserBooking(Long user_id, EventBooking booking) {
        Optional<User> optionalUser=this.userRepository.getUserById(user_id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            user.getBookings().add(booking);
            return Optional.of(this.userRepository.save(user));
        }
        return Optional.empty();
    }



    @Override
    public Optional<User> getUserById(Long id) {

        return this.userRepository.getUserById(id);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return this.userRepository.getUsersByName(name);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return this.userRepository.getUserByName(name);
    }



    @Override
    public User register(String username, String password, String repeatedPassword,
                         String name, String surname, Role role) throws RuntimeException {

        if(username==null || password==null || repeatedPassword==null || name==null || surname==null
                || username.isEmpty() || password.isEmpty() || repeatedPassword.isEmpty()
                || name.isEmpty() || surname.isEmpty()){
            throw new RuntimeException("Please fill all fields");
        }

        if(!password.equals(repeatedPassword)){
            throw new RuntimeException("Both passwords does not match ");
        }
        if(this.userRepository.getUserByUsername(username).isPresent()){
            throw new RuntimeException("User with username :"+username+" already exists");
        }

        User newUser=new User(username,name,surname,passwordEncoder.encode(password),role);

        return userRepository.save(newUser);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser=this.userRepository.getUserByUsername(username);

        if(optionalUser.isEmpty()) throw new UsernameNotFoundException(username);
        return optionalUser.get();
    }


}

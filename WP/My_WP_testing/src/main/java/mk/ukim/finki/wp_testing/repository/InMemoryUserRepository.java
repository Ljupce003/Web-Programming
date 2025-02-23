package mk.ukim.finki.wp_testing.repository;

import mk.ukim.finki.wp_testing.bootstrap.DataHolder;
import mk.ukim.finki.wp_testing.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    /**
     * This method searches and returns an Optional containing a User object if it is found,
     * else an empty Optional is returned
     */
    public Optional<User> findByUsername(String username) {
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    /**
     * This method searches and returns an Optional containing a User object if it is found,
     * else an empty Optional is returned
     */
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();
    }



    /**
     * This method adds a new user to the memory,but if a user with the same username as the provided user exists,
     * then the previous user is deleted
     */
    public User saveOrUpdate(User user) {
        DataHolder.users.removeIf(prev_user -> prev_user.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }

    /**
     * This method searches and removes if it finds a user with the same username as the provided one
     */
    public void deleteByUsername(String username) {
        DataHolder.users.removeIf(user -> user.getUsername().equals(username));
    }
}


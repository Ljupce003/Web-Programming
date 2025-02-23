package mk.ukim.finki.av3_project.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String surname;

    private String username;
    private String password;
}

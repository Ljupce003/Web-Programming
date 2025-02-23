package mk.ukim.finki.web_prog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Category {

    private String name;
    private String description;
}

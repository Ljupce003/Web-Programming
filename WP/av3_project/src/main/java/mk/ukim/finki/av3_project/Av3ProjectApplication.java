package mk.ukim.finki.av3_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Av3ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Av3ProjectApplication.class, args);
    }

}

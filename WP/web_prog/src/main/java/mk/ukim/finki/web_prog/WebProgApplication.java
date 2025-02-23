package mk.ukim.finki.web_prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WebProgApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebProgApplication.class, args);
    }

}

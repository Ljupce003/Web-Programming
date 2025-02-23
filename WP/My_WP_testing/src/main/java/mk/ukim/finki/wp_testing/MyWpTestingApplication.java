package mk.ukim.finki.wp_testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MyWpTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWpTestingApplication.class, args);
    }

}

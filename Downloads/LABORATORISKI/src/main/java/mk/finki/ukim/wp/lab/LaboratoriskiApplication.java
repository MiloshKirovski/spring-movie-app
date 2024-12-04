package mk.finki.ukim.wp.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class LaboratoriskiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaboratoriskiApplication.class, args);
    }

}

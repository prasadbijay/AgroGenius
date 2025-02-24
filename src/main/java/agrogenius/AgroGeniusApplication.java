package agrogenius;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan("agrogenius.controller.entity") 
@ComponentScan(basePackages = "agrogenius") 

@SpringBootApplication
public class AgroGeniusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgroGeniusApplication.class, args);
    }
}

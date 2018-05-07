package dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */

@SpringBootApplication
public class MainAppClass extends SpringBootServletInitializer{

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MainAppClass.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(MainAppClass.class, args);
    }
}

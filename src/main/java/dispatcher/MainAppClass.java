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

    /*Раскомметнировать переопределенный метод здесь.
    В классе pom.xml:
    1) выбрать <packaging>war</packaging> и закомментировать jar
    2) раскомментировать <start-class>dispatcher.MainAppClass</start-class>
    3) раскоментировать
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>


    */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MainAppClass.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(MainAppClass.class, args);
    }
}

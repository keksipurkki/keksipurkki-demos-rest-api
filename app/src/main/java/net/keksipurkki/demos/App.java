package net.keksipurkki.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String... args) {
        var app = new SpringApplication(App.class);
        app.run(args);
    }

}

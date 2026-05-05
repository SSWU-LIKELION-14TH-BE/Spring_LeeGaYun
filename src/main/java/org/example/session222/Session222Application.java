package org.example.session222;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Session222Application {

    public static void main(String[] args) {
        SpringApplication.run(Session222Application.class, args);
    }

}

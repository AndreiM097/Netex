package com.company.Netex;
import com.company.Netex.model.QMovie;
import com.querydsl.core.types.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

@SpringBootApplication
@PropertySource({"application.properties"})
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Main.class, args);

    }
}

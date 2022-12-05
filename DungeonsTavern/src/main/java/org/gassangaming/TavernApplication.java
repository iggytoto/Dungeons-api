package org.gassangaming;

import org.gassangaming.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {User.class})
public class TavernApplication {

    public static void main(String... args) {
        SpringApplication.run(TavernApplication.class, args);
    }

}

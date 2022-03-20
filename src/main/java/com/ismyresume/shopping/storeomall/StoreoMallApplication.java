package com.ismyresume.shopping.storeomall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreoMallApplication {

    public static void main(String[] args) {

        SpringApplication  app = new SpringApplication(StoreoMallApplication.class);
        app.setAdditionalProfiles("dev");
        app.run(args);

    }

}

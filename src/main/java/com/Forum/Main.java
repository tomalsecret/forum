package com.Forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Tomal on 2017-05-13.
 */
@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}

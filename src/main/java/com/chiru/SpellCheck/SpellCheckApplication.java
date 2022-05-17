package com.chiru.SpellCheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chiru")
public class SpellCheckApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpellCheckApplication.class, args);
    }

}

package com.okta.desafiospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DesafiospringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafiospringApplication.class, args);
    }

    @RestController
    @CrossOrigin("http://localhost:8081")
    class CaffeineLevelRestController {

        private SecureRandom random = new SecureRandom();

        String getCaffeineLevel() {
            List<String> givenList = Arrays.asList(
                    "Head on table asleep. Needs coffee now!",
                    "Not at all. What's wrong?!",
                    "Mildly. Boring.",
                    "Making progress.",
                    "Everything is awesome. Stuff is definitely happening.",
                    "Eyeballs are rolling around in my head and I'm shouting at my coworker about JHipster.",
                    "The LD50 of caffeine is 100 cups. Your developer has had 99 and is talking to the bike rack outside while jogging in place."
            );
            return givenList.get(this.random.nextInt(givenList.size()));
        }

        @GetMapping("/howcaffeinatedami")
        public String getCaffeineLevel(Principal principal) {
            String userName = principal != null ? principal.getName() : "Anonymous";
            return userName + ", your developer's caffeine level is: " + getCaffeineLevel();
        }
    }

    @Configuration
    @EnableWebSecurity
    class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and()
                    .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                    .oauth2ResourceServer().jwt();
        }
    }
}
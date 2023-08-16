package dev.thewlabs.schoolar;

import dev.thewlabs.schoolar.shared.utils.RandomUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    @ResponseBody
    @Operation(hidden = true)
    public String sayHello() {
        List<String> quotes = List.of(
                "You talking to me? - Taxi Driver",
                "I'm going to make him an offer he can't refuse. - The Godfather",
                "May the Force be with you. - Star Wars",
                "You're gonna need a bigger boat. - Jaws",
                "Say “hello” to my little friend! - Scarface",
                "Bond. James Bond. - Dr. No",
                "I see dead people. - The Sixth Sense",
                "Houston, we have a problem. - Apollo 13"
        );

        return RandomUtils.element(quotes);
    }
}

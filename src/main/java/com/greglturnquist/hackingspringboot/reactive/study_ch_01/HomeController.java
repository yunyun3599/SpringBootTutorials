package com.greglturnquist.hackingspringboot.reactive.study_ch_01;

import reactor.core.publisher.Mono;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    Mono<String> home(){
        return Mono.just("home");
    }
}

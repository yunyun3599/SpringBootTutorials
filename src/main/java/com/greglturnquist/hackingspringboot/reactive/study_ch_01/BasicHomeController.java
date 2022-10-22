package com.greglturnquist.hackingspringboot.reactive.study_ch_01;

import reactor.core.publisher.Mono;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicHomeController {

    @GetMapping(value="/home-for-studying")
    Mono<String> home(){
        return Mono.just("home");
    }
}

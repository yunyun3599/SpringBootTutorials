package com.greglturnquist.hackingspringboot.reactive.study_basic;

import reactor.core.publisher.Flux;

public class KitchenService {
    Flux<Dish> getDishes() {
        return Flux.just(
                new Dish("Sesame chicken"),
                new Dish("Lo mein noodles, plain"),
                new Dish("Sweet & sour beef"));
    }
}

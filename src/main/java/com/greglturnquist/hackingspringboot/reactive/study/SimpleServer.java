package com.greglturnquist.hackingspringboot.reactive.study;

import reactor.core.publisher.Flux;

public class SimpleServer {
    private final KitchenService kitchen;

    SimpleServer(KitchenService kitchen) {
        this.kitchen = kitchen;
    }

    Flux<Dish> doingMyJob() {         // 주방에서 요리를 가져오는 기능
        return this.kitchen.getDishes()
                .map(dish -> Dish.deliver(dish));   // 요리 완성 후 해야할 일을 map함수를 호출해 지정
    }
}

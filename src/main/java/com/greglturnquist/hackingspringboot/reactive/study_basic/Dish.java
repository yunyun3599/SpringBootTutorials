package com.greglturnquist.hackingspringboot.reactive.study_basic;


public class Dish {
    private String description;
    private Boolean delivered = false;

    Dish(String description) {
        this.description = description;
    }

    public static Dish deliver(Dish dish) {
        dish.delivered = true;
        return dish;
    }
}

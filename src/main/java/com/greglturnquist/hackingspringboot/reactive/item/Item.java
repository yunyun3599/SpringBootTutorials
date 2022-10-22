package com.greglturnquist.hackingspringboot.reactive.item;

import org.springframework.data.annotation.Id;

public class Item {
    private @Id String id;
    private String name;
    private double price;

    private Item() {}

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    // getter, setter, equals() 및 hashcode() 메소드는 편의상 생략
}

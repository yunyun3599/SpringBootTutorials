package com.greglturnquist.hackingspringboot.reactive.cart;

import com.greglturnquist.hackingspringboot.reactive.cartitem.CartItem;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private @Id String id;
    private List<CartItem> cartItems;

    private Cart() {}

    public Cart(String id) {
        this(id, new ArrayList<>());
    }

    public Cart(String id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }
    //게터, 세터와 equals() 및 hashcode() 메소드는 편의상 생략
}

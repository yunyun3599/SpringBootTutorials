package com.greglturnquist.hackingspringboot.reactive.cartitem;

import com.greglturnquist.hackingspringboot.reactive.Item.Item;

public class CartItem {

    private Item item;
    private int quantity;

    private CartItem() {}

    CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }
    // 게터, 세터와 equals() 및 hashcode() 메소드는 편의상 생략
}

package com.greglturnquist.hackingspringboot.reactive;

import com.greglturnquist.hackingspringboot.reactive.cart.Cart;
import com.greglturnquist.hackingspringboot.reactive.cart.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.cart.CartService;
import com.greglturnquist.hackingspringboot.reactive.cartitem.CartItem;
import com.greglturnquist.hackingspringboot.reactive.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private ItemRepository itemRepository;
    private CartRepository cartRepository;
    private CartService cartService;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository, CartService cartService) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.cartService = cartService;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(
                Rendering.view("home.html")
                        .modelAttribute("items", this.cartService.getInventory().doOnNext(System.out::println))
                        .modelAttribute("cart", this.cartService.getCart("My Cart")
                                .defaultIfEmpty(new Cart("My Cart")))
                        .build()
        );
    }

    @Deprecated
    @PostMapping("/deprecated/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.cartRepository.findById("My Cart")
                .defaultIfEmpty(new Cart("My Cart"))
                .flatMap(cart -> cart.getCartItems().stream()
                        .filter(cartItem -> cartItem.getItem()
                                .getId().equals(id))
                        .findAny()
                        .map(cartItem -> {
                            cartItem.increment();
                            return Mono.just(cart);
                        })
                        .orElseGet(() -> {
                            return this.itemRepository.findById(id)
                                    .map(item -> new CartItem(item))
                                    .map(cartItem -> {
                                        cart.getCartItems().add(cartItem);
                                        return cart;
                                    });
                        }))
                .flatMap(cart -> this.cartRepository.save(cart))
                .thenReturn("redirect:/");
    }

}

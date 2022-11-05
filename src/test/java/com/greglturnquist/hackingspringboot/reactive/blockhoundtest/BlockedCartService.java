package com.greglturnquist.hackingspringboot.reactive.blockhoundtest;

import com.greglturnquist.hackingspringboot.reactive.cart.Cart;
import com.greglturnquist.hackingspringboot.reactive.cart.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.cartitem.CartItem;
import com.greglturnquist.hackingspringboot.reactive.item.ItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BlockedCartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    BlockedCartService(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    Mono<Cart> addToCart(String cartId, String itemId) {
        Cart myCart = this.cartRepository.findById(cartId)
                .defaultIfEmpty(new Cart(cartId))
                .block();       //블로킹 코드 호출

        return myCart.getCartItems().stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
                .findAny()
                .map(cartItem -> {
                    cartItem.increment();
                    return Mono.just(myCart);
                })
                .orElseGet(() -> this.itemRepository.findById(itemId)
                        .map(item -> new CartItem(item))
                        .map(cartItem -> {
                            myCart.getCartItems().add(cartItem);
                            return myCart;
                        }))
                .flatMap(cart -> this.cartRepository.save(cart));
    }
}

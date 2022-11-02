package com.greglturnquist.hackingspringboot.reactive.cart;

import com.greglturnquist.hackingspringboot.reactive.cartitem.CartItem;
import com.greglturnquist.hackingspringboot.reactive.item.Item;
import com.greglturnquist.hackingspringboot.reactive.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CartServiceUnitTest {

    @MockBean private ItemRepository itemRepository;
    @MockBean private CartRepository cartRepository;

    private CartService cartService;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 정의
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

        // 협력자와의 상호작용 정의
        when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

        cartService = new CartService(itemRepository, cartRepository);
    }

    @Test
    void addItemToEmptyCartShouldProduceOneCartItem() {
        cartService.addToCart("My Cart", "item1")
                .as(StepVerifier::create)
                .expectNextMatches(cart -> {
                    Assertions.assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);
                    Assertions.assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));
                    return true;
                })
                .verifyComplete();
    }

    @Test
    void alternativeWayToTest() {
        StepVerifier.create(
                cartService.addToCart("My Cart", "item1")
        ).expectNextMatches(cart -> {
            Assertions.assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                    .containsExactlyInAnyOrder(1);
            Assertions.assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                    .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));
            return true;
        })
        .verifyComplete();
    }
}

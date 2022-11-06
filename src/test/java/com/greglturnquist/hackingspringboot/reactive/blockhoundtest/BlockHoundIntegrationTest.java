package com.greglturnquist.hackingspringboot.reactive.blockhoundtest;

import com.greglturnquist.hackingspringboot.reactive.cart.Cart;
import com.greglturnquist.hackingspringboot.reactive.cart.CartRepository;
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

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BlockHoundIntegrationTest {

    BlockedCartService cartService;

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 정의
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

        // 협력자와 가짜 상호작용 정의
        when(cartRepository.findById(anyString()))
                .thenReturn(Mono.<Cart> empty().hide());

        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

         cartService = new BlockedCartService(itemRepository, cartRepository);
    }

//    @Test
//    void blockHoundShouldTrapBlockingCall() {
//        Mono.delay(Duration.ofSeconds(1))
//                .flatMap(tick -> cartService.addToCart("My Cart", "item1"))
//                .as(StepVerifier::create)
//                .verifyErrorSatisfies(throwable -> {
//                    assertThat(throwable).hasMessageContaining(
//                            "block()/blockFirst()/blockLast() are blocking"
//                    );
//                });
//    }
}

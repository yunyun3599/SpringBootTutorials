package com.greglturnquist.hackingspringboot.reactive.slicetest;

import com.greglturnquist.hackingspringboot.reactive.HomeController;
import com.greglturnquist.hackingspringboot.reactive.cart.Cart;
import com.greglturnquist.hackingspringboot.reactive.cart.CartRepository;
import com.greglturnquist.hackingspringboot.reactive.cart.CartService;
import com.greglturnquist.hackingspringboot.reactive.item.Item;
import com.greglturnquist.hackingspringboot.reactive.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest(HomeController.class)
public class HomeControllerSliceTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    CartService cartService;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    CartRepository cartRepository;

    @Test
    void homePage() {
        when(cartService.getInventory()).thenReturn(Flux.just(
                new Item("id1", "name1", "desc1", 1.99),
                new Item("id2", "name2", "desc2", 9.99)
        ));

        when(cartService.getCart("My Cart"))
                .thenReturn(Mono.just(new Cart("My Cart")));

        client.get().uri("/").exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(exchangeResult -> {
                    assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id1\"");
                    assertThat(exchangeResult.getResponseBody()).contains("action=\"/add/id2\"");
                });
    }

}

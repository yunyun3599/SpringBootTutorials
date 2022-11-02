package com.greglturnquist.hackingspringboot.reactive.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemUnitTest {

    @Test
    void itemBasicsSouldWork() {
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);

        //AssertJ를 이용한 값 일치 테스트
        Assertions.assertThat(sampleItem.getId()).isEqualTo("item1");
        Assertions.assertThat(sampleItem.getName()).isEqualTo("TV tray");
        Assertions.assertThat(sampleItem.getDescription()).isEqualTo("Alf TV tray");
        Assertions.assertThat(sampleItem.getPrice()).isEqualTo(19.99);

        Assertions.assertThat(sampleItem.toString())
                .isEqualTo("Item{id='item1', name='TV tray', description='Alf TV tray', price=19.99}");

        Item sampleItem2 = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        Assertions.assertThat(sampleItem).isEqualToComparingFieldByField(sampleItem2);
    }
}

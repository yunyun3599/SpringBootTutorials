package com.greglturnquist.hackingspringboot.reactive;

import com.greglturnquist.hackingspringboot.reactive.item.Item;
import org.springframework.data.repository.CrudRepository;

@Deprecated
public interface BlockingItemRepository extends CrudRepository<Item, String> {

}

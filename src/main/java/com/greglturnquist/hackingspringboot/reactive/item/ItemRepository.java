package com.greglturnquist.hackingspringboot.reactive.item;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {
    Flux<Item> findByNameContaining(String partialName);

    Flux<Item> findByNameContainingIgnoreCase(String partialName);

    Flux<Item> findByDescriptionContainingIgnoreCase(String partialName);

    Flux<Item> findByNameContainingAndDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);

    Flux<Item> findByNameContainingOrDescriptionContainingAllIgnoreCase(String partialName, String partialDesc);

//    @Query("{'name' : ?0, 'age' :  ?1}")
//    Flux<Item> findItemByCustomerMonthlyReport(String name, int age);
//
//    @Query(sort = "{'age' : -1}")
//    Flux<Item> findSortedStuffForWeeklyReport();
}

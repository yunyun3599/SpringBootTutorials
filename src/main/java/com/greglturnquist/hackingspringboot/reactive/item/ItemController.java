package com.greglturnquist.hackingspringboot.reactive.item;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController (ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    public Flux<Item> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam boolean useAnd) {
        return itemService.searchByExample(name, description, useAnd);
    }

}

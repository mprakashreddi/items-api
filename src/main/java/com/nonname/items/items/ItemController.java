package com.nonname.items.items;


import com.nonname.items.client.database.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Item> save(@RequestBody Item item) {
        return itemService.save(item);
    }

    @GetMapping("/{id}")
    public Mono<Item> findById(@PathVariable("id") Long id) {
        return itemService.findById(id);
    }

    @GetMapping
    public Flux<Item> findAll() {
        return itemService.findAll();
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return itemService.deleteById(id);
    }
}
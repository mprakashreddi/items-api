package com.nonname.items.items;

import com.nonname.items.client.database.Item;
import com.nonname.items.client.database.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Mono<Item> save(Item item) {
        return Mono.just(itemRepository.save(item));
    }

    public Mono<Item> findById(Long id) {
        return Mono.just(itemRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    public Flux<Item> findAll() {
        return Flux.fromIterable(itemRepository.findAll());
    }

    public Mono<Void> deleteById(Long id) {
        itemRepository.deleteById(id);
        return Mono.empty();
    }
}

package com.nonname.items.files;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {

    String[] files = {"file1","file2","file3"};
    public Flux<String> listFiles(String fileDirectory) {
        return Flux.fromIterable(Arrays.asList(files));
    }

    public Mono<String> process(FileRequest request) {
        return Mono.just(UUID.randomUUID().toString());
    }
    public Flux<TransactionDetails> getTransactionDetails(String transactionId) {
        return Flux.fromArray(null);
    }


}

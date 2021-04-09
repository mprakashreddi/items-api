package com.nonname.items.files;


import com.nonname.items.client.database.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> processFiles(@RequestBody FileRequest fileRequest) {
        return fileService.process(fileRequest);
    }

    @GetMapping("/{fileDirectory}")
    public Mono<List<String>> listFiles(@PathVariable("fileDirectory") String fileDirectory) {
        return fileService.listFiles(fileDirectory).collectList();
    }

    @GetMapping("/transaction/{transactionId}")
    public Flux<TransactionDetails> getTransactionDetails(@PathVariable("transactionId") String transactionId) {
        return fileService.getTransactionDetails(transactionId);
    }
}
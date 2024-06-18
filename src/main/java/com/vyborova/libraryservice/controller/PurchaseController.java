package com.vyborova.libraryservice.controller;

import com.vyborova.libraryservice.models.Purchase;
import com.vyborova.libraryservice.services.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseController {

    @Autowired
    private KafkaSender kafkaSender;

    @PostMapping
    public void makePurchase(@RequestBody Purchase purchase) {
        String message = "Book  with id = " + purchase.getBook().getId() + " and cost = " + purchase.getBook().getPrice() + " was bought";
        kafkaSender.sendMessage(message);
    }
}


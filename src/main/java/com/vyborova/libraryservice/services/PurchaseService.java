package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.Purchase;
import com.vyborova.libraryservice.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}

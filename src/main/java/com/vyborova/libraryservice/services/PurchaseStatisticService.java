package com.vyborova.libraryservice.services;

import com.vyborova.libraryservice.models.AuthorStatisticView;
import com.vyborova.libraryservice.models.PurchasesStatisticView;
import com.vyborova.libraryservice.repositories.AuthorStatisticRepository;
import com.vyborova.libraryservice.repositories.PurchaseStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseStatisticService {
    private final PurchaseStatisticRepository purchaseStatisticRepository;

    @Autowired
    public PurchaseStatisticService(PurchaseStatisticRepository purchaseStatisticRepository) {
        this.purchaseStatisticRepository = purchaseStatisticRepository;
    }

    public List<PurchasesStatisticView> findAll() {
        return purchaseStatisticRepository.findAll();
    }

}

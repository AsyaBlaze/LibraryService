package com.vyborova.libraryservice.repositories;

import com.vyborova.libraryservice.models.PurchasesStatisticView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseStatisticRepository extends ReadOnlyRepository<PurchasesStatisticView, UUID> {
}

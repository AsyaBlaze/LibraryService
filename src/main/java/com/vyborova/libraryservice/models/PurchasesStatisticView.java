package com.vyborova.libraryservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.UUID;

@Table(name = "purchases_statistic")
@Immutable
@Entity
@Getter
public class PurchasesStatisticView {
    @Id
    private UUID id;
    private String authorName;
    private Long bookSum;
}

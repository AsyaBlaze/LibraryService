package com.vyborova.libraryservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "purchase_date", columnDefinition = "DATE")
    private LocalDate purchaseDate;

    @Column(name = "buyer_name")
    private String buyerName;
}

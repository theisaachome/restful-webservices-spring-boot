package com.isaachome.bookstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdDate;
    @OneToMany
    @JoinTable(name = "jnd_order_line",
            joinColumns = @JoinColumn(name="order_fk"),
            inverseJoinColumns = @JoinColumn(name = "purchase_fk")
    )
    private List<OrderLine> orderLines;
}

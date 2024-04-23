package org.softwarephoenix.testzadanie.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private int quantity;
    private String deliveryAddress;
    private String phoneNumber;

}
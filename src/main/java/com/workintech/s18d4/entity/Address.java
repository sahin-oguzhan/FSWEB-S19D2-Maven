package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address", schema = "sprint19d2")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private Integer no;
    private String city;
    private String country;
    private String description;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, mappedBy = "address")
    private Customer customer;
}

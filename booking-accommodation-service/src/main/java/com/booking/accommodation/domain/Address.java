package com.booking.accommodation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
    @NotBlank
    @Column(name = "address_country", nullable = false)
    private String country;
    
    @NotBlank
    @Column(name = "address_city", nullable = false)
    private String city;
    
    @NotBlank
    @Column(name = "address_street", nullable = false)
    private String street;
    
    @Positive
    @Column(name = "address_num", nullable = false)
    private int number;
}
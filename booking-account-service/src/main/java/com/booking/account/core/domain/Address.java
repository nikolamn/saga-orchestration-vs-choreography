package com.booking.account.core.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    
    @NotBlank(message = "Country must be specified")
    @Column(name = "address_country", nullable = false)
    private String country;
    
    @NotBlank(message = "City must be specified")
    @Column(name = "address_city", nullable = false)
    private String city;
    
    @NotBlank(message = "Street must be specified")
    @Column(name = "address_street", nullable = false)
    private String street;
    
    @Positive(message = "Invalid street number")
    @Column(name = "address_num", nullable = false)
    private int number;
}
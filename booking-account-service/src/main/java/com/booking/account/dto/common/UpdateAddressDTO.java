package com.booking.account.dto.common;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressDTO {

    private String country;

    private String city;

    private String street;

    @Positive(message = "Invalid street number")
    private int number;
}

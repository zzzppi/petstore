package com.petstore.mockito;

import java.util.Optional;

public class Address {
    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }

    public Address(Country country) {
        this.country = country;
    }
}

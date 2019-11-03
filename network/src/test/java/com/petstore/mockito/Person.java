package com.petstore.mockito;

import java.util.Optional;

public class Person {
    private String name;
    private String contactNo;
    private String profession;
    private Address address;

    public Person(String name, String contactNo) {
        System.out.println("Invoked Person constructor");
        this.name = name;
        this.contactNo = contactNo;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setProfession(String journalist) {
        this.profession = journalist;

    }

    public Optional<String> getProfession() {
        return Optional.ofNullable(profession);
    }
}

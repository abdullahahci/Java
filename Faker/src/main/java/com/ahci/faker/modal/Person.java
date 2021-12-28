package com.ahci.faker.modal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private String firstName;
    private String lastName;
    private Address address;

    public String getCountry(){return address.getCountry();}
}

package com.ahci.faker.modal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String city;
    private String postalCode;
    private String country;
    private String number;
    private String fulladress;
}

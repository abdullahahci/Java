package com.ahci.faker.modal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Commerce {
    private String price;
    private String color;
    private String department;
    private String promotionCode;
    private String material;
    private String productName;
}

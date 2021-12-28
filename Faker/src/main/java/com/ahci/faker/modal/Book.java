package com.ahci.faker.modal;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String author;
    private String genre;
    private String publisher;
    private String title;
    private String price;
}
